package com.dotin.servlets;

import com.dotin.dao.CategoryDao;
import com.dotin.dao.EmailDao;
import com.dotin.dao.EmployeeDao;
import com.dotin.dao.VacationDAo;
import com.dotin.dto.Inbox;
import com.dotin.model.CategoryElement;
import com.dotin.model.Email;
import com.dotin.model.Employee;
import com.dotin.model.Vacation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*@WebServlet("/")*/
public class __EmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EmployeeDao employeeDao;
    private CategoryDao categoryDao;
    private VacationDAo vacationDAo;
    private EmailDao emailDao;

    public void init() {
        employeeDao = new EmployeeDao();
        categoryDao = new CategoryDao();
        vacationDAo = new VacationDAo();
        emailDao = new EmailDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {

            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertEmployee(request, response);
                    break;
                case "/delete":
                    deleteEmployee(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateEmployee(request, response);
                    break;
                case "/insertVacation":
                    insertVacation(request, response);
                    break;
                case "/vacation":
                    showNewVacationRequestForm(request, response);
                    break;
                case "/checkVacation":
                    checkVacation(request, response);
                    break;
                case "/confirmVacation":
                    vactionConfirm(request, response);
                    break;
                case "/rejectVacation":
                    vactionReject(request, response);
                    break;
                case "/cancelVacation":
                    vactionCancel(request, response);
                    break;
                case "/Email":
                    showNewEmailForm(request, response);
                    break;
                case "/insertEmail":
                    insertEmail(request, response);
                    break;
                default:
                    listEmployee(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    private void listEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Employee> listEmployee = employeeDao.getAllEmployee();
        request.setAttribute("listEmployee", listEmployee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-employee.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CategoryElement> roles = categoryDao.getAllRoles();
        request.setAttribute("roles", roles);
        List<Employee> manager = employeeDao.getAllmanager();
        request.setAttribute("manager", manager);
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<CategoryElement> roles = categoryDao.getAllRoles();
        request.setAttribute("roles", roles);
        List<Employee> manager = employeeDao.getAllmanager();
        request.setAttribute("manager", manager);
        Long id = Long.parseLong(request.getParameter("id"));
        Employee existingEmployee = employeeDao.getEmployee(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("employee-form.jsp");
        request.setAttribute("employee", existingEmployee);
        dispatcher.forward(request, response);

    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int age = Integer.parseInt(request.getParameter("age"));
        Long roleId = Long.parseLong(request.getParameter("role"));
        Long managerId = request.getParameter("managerId") != null ? Long.parseLong(request.getParameter("managerId")) : null;
        String address = request.getParameter("address");
        //boolean isActive = request.getParameter("active") != null ? Boolean.parseBoolean(request.getParameter("active")) : false;
        boolean isActive = true;

        Employee newEmployee = new Employee();
        newEmployee.setName(name);
        newEmployee.setLastName(lastName);
        newEmployee.setEmail(email);
        newEmployee.setPhone(phone);
        newEmployee.setAge(age);
        newEmployee.setAddress(address);
        newEmployee.setActive(isActive);

        newEmployee.setRole(roleId != null ? categoryDao.getCategoryElement(roleId) : null);
        newEmployee.setManager(managerId != null ? employeeDao.getEmployee(managerId) : null);

        //newEmployee.setActive(true);

        employeeDao.saveEmployee(newEmployee
        );
        response.sendRedirect("list");
    }

    private void insertVacation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        SimpleDateFormat formatter6 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String toDate = request.getParameter("to");
        String fromDate = request.getParameter("from");

        String description = request.getParameter("description");
        Long userID = Long.parseLong(request.getParameter("id"));
        Vacation vacation = new Vacation();
        vacation.setDescription(description);
        try {
            Date from = formatter6.parse(fromDate);
            Date to = formatter6.parse(toDate);
            vacation.setFrom(from);
            vacation.setTo(to);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Employee employee = employeeDao.getEmployee(userID);
        vacation.setPerson(employee);
        vacationDAo.saveVacation(vacation);
        response.sendRedirect("list");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Integer age = Integer.parseInt(request.getParameter("age"));
        Long roleId = Long.parseLong(request.getParameter("role"));
        Long managerId = request.getParameter("managerId") != null ? Long.parseLong(request.getParameter("managerId")) : null;
        String address = request.getParameter("address");
        Boolean isActive = request.getParameter("active") != null ? Boolean.parseBoolean(request.getParameter("active")) : false;
        ///Boolean isActive = request.getParameterValues("active");
        //Employee employee = new Employee();
        Employee employee = employeeDao.getEmployee(id);
        employee.setId(id);
        employee.setName(name);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setAge(age);
        employee.setAddress(address);
        employee.setActive(isActive);

        employee.setRole(roleId != null ? categoryDao.getCategoryElement(roleId) : null);
        employee.setManager(managerId != null ? employeeDao.getEmployee(managerId) : null);

        employeeDao.updateEmployee(employee);
        response.sendRedirect("list");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Employee employee = employeeDao.getEmployee(id);
        employeeDao.deleteEmployee(employee);
        response.sendRedirect("list");
    }

    private void checkVacation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        List<Employee> employees = employeeDao.getManagerEmployees(id);
        List<Vacation> vacations = new ArrayList<>();
        for (Employee employee : employees) {
            List<Vacation> vacation = vacationDAo.getPersonVacations(employee.getId());
            if (vacation != null)
                vacations.addAll(vacation);
        }
        request.setAttribute("vacations", vacations);
        request.setAttribute("userId", id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-vacation.jsp");
        dispatcher.forward(request, response);

    }

    private void showNewVacationRequestForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Employee employee = employeeDao.getEmployee(id);

        request.setAttribute("id", id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("vacation-form.jsp");
        dispatcher.forward(request, response);
    }

    private void vactionConfirm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Long userId = Long.parseLong(request.getParameter("userId"));
        Vacation vacation = vacationDAo.getVacation(id);
        CategoryElement confirmed = categoryDao.getCategoryElement((long) 4);
        vacation.setState(confirmed);
        vacationDAo.updateVacation(vacation);
        request.setAttribute("id", id);
        response.sendRedirect("checkVacation?id=" + userId);
    }

    private void vactionReject(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Long userId = Long.parseLong(request.getParameter("userId"));
        Vacation vacation = vacationDAo.getVacation(id);
        CategoryElement confirmed = categoryDao.getCategoryElement((long) 5);
        vacation.setState(confirmed);
        vacationDAo.updateVacation(vacation);
        request.setAttribute("id", id);
        response.sendRedirect("checkVacation?id=" + userId);
    }

    private void vactionCancel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Long userId = Long.parseLong(request.getParameter("userId"));
        Vacation vacation = vacationDAo.getVacation(id);
        CategoryElement confirmed = categoryDao.getCategoryElement((long) 6);
        vacation.setState(confirmed);
        vacationDAo.updateVacation(vacation);
        request.setAttribute("id", id);
        response.sendRedirect("checkVacation?id=" + userId);
    }

    private void showNewEmailForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Employee employee = employeeDao.getEmployee(id);

        List<Email> emails = emailDao.getEmailByReceiver(id);
        List<Inbox> inboxList = new ArrayList<>();
        for (Email email : emails) {
            Employee em = employeeDao.getEmployee(email.getSender());
            Inbox inbox = new Inbox();
            inbox.setDate(email.getDate());
            inbox.setEmail(em.getEmail());
            inbox.setName(em.getName());
            inbox.setLastName(em.getLastName());
            inbox.setId(email.getId());
            inbox.setMessage(email.getMessage());
            inbox.setSubject(email.getSubject());
            inboxList.add(inbox);
        }
        List<Email> Sendedemails = emailDao.getEmailBySender(id);
        List<Inbox> outboxList = new ArrayList<>();
        for (Email email : Sendedemails) {
            Employee em = employeeDao.getEmployee(email.getReceiver());
            Inbox inbox = new Inbox();
            inbox.setDate(email.getDate());
            inbox.setEmail(em.getEmail());
            inbox.setName(em.getName());
            inbox.setLastName(em.getLastName());
            inbox.setId(email.getId());
            inbox.setMessage(email.getMessage());
            inbox.setSubject(email.getSubject());
            outboxList.add(inbox);
        }

        List<Employee> allEmails = employeeDao.getAllEmployee();
       /* List<Employee> senders = new ArrayList<>();
        senders.addAll(receivers);
        request.setAttribute("senders", senders);
        allEmails.removeIf(t -> t.getId() == employee.getId());*/
        RequestDispatcher dispatcher = request.getRequestDispatcher("email-form.jsp");
        request.setAttribute("allEmails", allEmails);
        request.setAttribute("inboxList", inboxList);
        request.setAttribute("outboxList", outboxList);
        request.setAttribute("id", id);
        dispatcher.forward(request, response);
    }

    private void insertEmail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        //Long personId = Long.parseLong(request.getParameter("personId"));
        String receiver = request.getParameter("receiver");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        Email email = new Email();
        email.setSender(employeeDao.getEmployee(id).getId());
        email.setReceiver(employeeDao.getEmployeeByEmail(receiver).getId());
        email.setSubject(subject);
        email.setMessage(message);
        email.setDate(new Date());

        emailDao.saveEmail(email);
        response.sendRedirect("list");
    }
}
