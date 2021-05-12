package com.dotin.servlets;

import com.dotin.JalaliCalendar;
import com.dotin.dao.CategoryDao;
import com.dotin.dao.EmployeeDao;
import com.dotin.dao.VacationDAo;
import com.dotin.model.CategoryElement;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/vacation/*")
public class VacationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EmployeeDao employeeDao;
    private CategoryDao categoryDao;
    private VacationDAo vacationDAo;

    public void init() {
        employeeDao = new EmployeeDao();
        categoryDao = new CategoryDao();
        vacationDAo = new VacationDAo();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        try {

            switch (action) {
                case "/vacation/insertVacation":
                    insertVacation(request, response);
                    break;
                default:
                    response.sendRedirect("/");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getRequestURI();

        try {

            switch (action) {
                case "/vacation":
                    showNewVacationRequestForm(request, response);
                    break;
                case "/vacation/checkVacation":
                    checkVacation(request, response);
                    break;
                case "/vacation/confirmVacation":
                    vactionConfirm(request, response);
                    break;
                case "/vacation/rejectVacation":
                    vactionReject(request, response);
                    break;
                case "/vacation/cancelVacation":
                    vactionCancel(request, response);
                    break;
                default:
                    response.sendRedirect("/");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    private void insertVacation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        JalaliCalendar jalaliCalendar = new JalaliCalendar();
        String toDate = request.getParameter("to");
        String fromDate = request.getParameter("from");
        String description = request.getParameter("description");
        Long userID = Long.parseLong(request.getParameter("id"));
        Vacation vacation = new Vacation();
        vacation.setDescription(description);

        Date from = jalaliCalendar.getGregorianDate(fromDate);
        Date to = jalaliCalendar.getGregorianDate(toDate);
        vacation.setFrom(from);
        vacation.setTo(to);


        Employee employee = employeeDao.getEmployee(userID);
        vacation.setPerson(employee);
        vacationDAo.saveVacation(vacation);
        response.sendRedirect("list");
    }

    private void showNewVacationRequestForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Employee employee = employeeDao.getEmployee(id);

        request.setAttribute("id", id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/vacation-form.jsp");
        dispatcher.forward(request, response);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/list-vacation.jsp");
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

    private void listEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Employee> listEmployee = employeeDao.getAllEmployee();
        request.setAttribute("listEmployee", listEmployee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/list-employee.jsp");
        dispatcher.forward(request, response);
    }
}
