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
    <li><a href="#Email management">Email management</a></li>
</ul>
<center>
    <h1>Vacation Management</h1>

</center>
<div align="center">
    <table border="1" cellpadding="5" bgcolor=#f2f2f2 style="color:white">
        <caption><h2>List of Employee</h2></caption>
        <tr bgcolor=#4CAF50>
            <th>ID</th>
            <th>state</th>
            <th>EmployeeId</th>
            <th>EmployeeName</th>
            <th>DateFrom</th>
            <th>DateTo</th>
            <th>description</th>
            <th></th>
        </tr>
        <c:forEach var="vacation" items="${vacations}">
            <tr style="color:black">
                <td><c:out value="${vacation.id}"/></td>
                <td><c:out value="${vacation.getState().name}"/></td>
                <td><c:out value="${vacation.getPerson().id}"/></td>
                <td><c:out value="${vacation.getPerson().name}"/></td>
                <td><c:out value="${vacation.from}"/></td>
                <td><c:out value="${vacation.to}"/></td>
                <td><c:out value="${vacation.description}"/></td>
                <td>
                    <c:choose>
                        <c:when test="${empty vacation.state || vacation.getState().id!=4}">
                            <a href="confirmVacation?id=<c:out value='${vacation.id}'/>&userId=<c:out value='${userId}' />">Confirm</a>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${ empty vacation.state || vacation.getState().id!=5}">
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="rejectVacation?id=<c:out value='${vacation.id}' />&userId=<c:out value='${userId}' />">Reject</a>
                        </c:when>
                    </c:choose>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <c:choose>
                        <c:when test="${not empty vacation.state && vacation.getState().id==4}">
                            <a href="cancelVacation?id=<c:out value='${vacation.id}' />&userId=<c:out value='${userId}' />">Cancel</a>
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