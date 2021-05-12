package com.dotin.servlets;

import com.dotin.dao.CategoryDao;
import com.dotin.dao.EmployeeDao;
import com.dotin.model.CategoryElement;
import com.dotin.model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/employee/*")
public class EmployeeServlet extends HttpServlet {

    private EmployeeDao employeeDao;
    private CategoryDao categoryDao;

    public void init() {
        employeeDao = new EmployeeDao();
        categoryDao = new CategoryDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        try {

            switch (action) {
                case "/employee/insert":
                    insertEmployee(request, response);
                    break;
                case "/employee/update":
                    updateEmployee(request, response);
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
                case "/employee/new":
                    showNewForm(request, response);
                    break;
                case "/employee/insert":
                    insertEmployee(request, response);
                    break;
                case "/employee/delete":
                    deleteEmployee(request, response);
                    break;
                case "/employee/edit":
                    showEditForm(request, response);
                    break;
                case "/employee/update":
                    updateEmployee(request, response);
                    break;
                default:
                    listEmployee(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CategoryElement> roles = categoryDao.getAllRoles();
        request.setAttribute("roles", roles);
        List<Employee> manager = employeeDao.getAllmanager();
        request.setAttribute("manager", manager);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/employee-form.jsp");
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

        employeeDao.saveEmployee(newEmployee
        );
        response.sendRedirect("/");
    }


    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Employee employee = employeeDao.getEmployee(id);
        employeeDao.deleteEmployee(employee);
        response.sendRedirect("/");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<CategoryElement> roles = categoryDao.getAllRoles();
        request.setAttribute("roles", roles);
        List<Employee> manager = employeeDao.getAllmanager();
        request.setAttribute("manager", manager);
        Long id = Long.parseLong(request.getParameter("id"));
        Employee existingEmployee = employeeDao.getEmployee(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/employee-form.jsp");
        request.setAttribute("employee", existingEmployee);
        dispatcher.forward(request, response);

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
        response.sendRedirect("/");
    }

    private void listEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Employee> listEmployee = employeeDao.getAllEmployee();
        request.setAttribute("listEmployee", listEmployee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/list-employee.jsp");
        dispatcher.forward(request, response);
    }

}

