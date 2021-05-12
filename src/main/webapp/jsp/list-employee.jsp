<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }

        li {
            float: left;
            border-right: 1px solid #bbb;
        }

        li:last-child {
            border-right: none;
        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        li a:hover:not(.active) {
            background-color: #111;
        }

        .active {
            background-color: #4CAF50;
        }
    </style>
    <title>Employee Management Application</title>
</head>
<body>
<ul>
    <li><a class="active" href="/">Home</a></li>
    <li><a href="#User management">User management</a></li>
    <li><a href="#vacation">Vacation request</a></li>
    <li><a href="#Email">Email management</a></li>
</ul>
<center>
    <h1>Employee Management</h1>
    <h2>
        <a href="employee/new">Add New Employee</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/">List All Employee</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5" bgcolor=#f2f2f2 style="color:black">
        <caption><h2>List of Employee</h2></caption>
        <tr bgcolor=#4CAF50>
            <th>ID</th>
            <th>Name</th>
            <th>LastName</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Age</th>
            <th>Role</th>
            <th>ManagerId</th>
            <th>Address</th>
            <%--<th>Active</th>--%>
            <th></th>
        </tr>
        <c:forEach var="employee" items="${listEmployee}">
            <tr style="${employee.active!=true ? 'background-color: #b3b3b3;' : 'background-color : white'}">
                    <%--<input style="${active!=true ? 'bgcolor=red' : ''}"/>--%>
                <td><c:out value="${employee.id}"/></td>
                <td><c:out value="${employee.name}"/></td>
                <td><c:out value="${employee.lastName}"/></td>
                <td><c:out value="${employee.email}"/></td>
                <td><c:out value="${employee.phone}"/></td>
                <td><c:out value="${employee.age}"/></td>
                <c:choose>
                    <c:when test="${not empty employee.role}">
                        <td><c:out value="${employee.getRole().name}"/></td>
                    </c:when>
                    <c:otherwise>
                        <td><c:out value=""/></td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${not empty employee.manager}">
                        <td><c:out value="${employee.getManager().name}"/></td>
                    </c:when>
                    <c:otherwise>
                        <td><c:out value=""/></td>
                    </c:otherwise>
                </c:choose>
                <td><c:out value="${employee.address}"/></td>
                <td>
                    <a href="employee/edit?id=<c:out value='${employee.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="employee/delete?id=<c:out value='${employee.id}' />">Delete</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="email?id=<c:out value='${employee.id}' />">Emails</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="vacation?id=<c:out value='${employee.id}' />">Vacation</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <c:choose>
                        <c:when test="${not empty employee.role && employee.getRole().id==1}">
                            <a href="vacation/checkVacation?id=<c:out value='${employee.id}' />">CheckLeave</a>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>