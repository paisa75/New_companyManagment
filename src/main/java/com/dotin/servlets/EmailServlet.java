package com.dotin.servlets;

import com.dotin.dao.EmailDao;
import com.dotin.dao.EmployeeDao;
import com.dotin.dto.Inbox;
import com.dotin.model.Email;
import com.dotin.model.Employee;

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

@WebServlet("/email/*")
public class EmailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EmployeeDao employeeDao;
    private EmailDao emailDao;

    public void init() {
        employeeDao = new EmployeeDao();
        emailDao = new EmailDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        try {

            switch (action) {
                case "/email/insert":
                    insertEmail(request, response);
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


        switch (action) {
            case "/email":
                showNewEmailForm(request, response);
                break;
            default:
                response.sendRedirect("/");
                break;
        }

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

        List<Employee> receivers = employeeDao.getAllEmployee();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/email-form.jsp");
        request.setAttribute("inboxList", inboxList);
        request.setAttribute("outboxList", outboxList);
        request.setAttribute("receivers", receivers);
        request.setAttribute("id", id);
        dispatcher.forward(request, response);
    }

    private void insertEmail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Long receiver = Long.parseLong(request.getParameter("receiver"));
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        Email email = new Email();
        email.setSender(employeeDao.getEmployee(id).getId());
        email.setReceiver(receiver);
        email.setSubject(subject);
        email.setMessage(message);
        email.setDate(new Date());

        emailDao.saveEmail(email);
        response.sendRedirect("list");
    }


}
