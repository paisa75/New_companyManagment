<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee Management Application</title>
    <script type="text/javascript">
        function validate() {
            var name = document.getElementById("name");
            var lastName = document.getElementById("lastName");
            var email = document.getElementById("email");
            var phone = document.getElementById("phone");
            var age = document.getElementById("age");
            var role = document.getElementById("role");
            var address = document.getElementById("address");
            var valid = true;
            if (name.value.length <= 0) {
                alert("please input your name!");
                return false;
            }
            if (lastName.value.length <= 0) {
                alert("please input your lastName!");
                return false;
            }
            if (email.value.length <= 0) {
                alert("please input your email!");
                return false;
            }
            if (phone.value.length <= 0) {
                alert("please input your phone!");
                return false;
            } else {
                if (isNaN(phone.value)) {
                    alert("Enter a number as your age");
                    return false;
                }
            }
            if (age.value.length <= 0) {
                alert("please input your age!");
                return false;
            } else {
                if (isNaN(age.value)) {
                    alert("Enter a number as your age");
                    return false;
                }
            }
            if (address.value.length <= 0) {
                alert("please input your address!");
                return false;
            }
            return valid;
        };
    </script>
</head>
<body>
<center>
    <h1>Employee Management</h1>
    <h2>
        <a href="employee/new">Add New Employee</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/">List All Employee</a>

    </h2>
</center>
<div align="center">
    <c:if test="${employee != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${employee == null}">
        <form action="insert" method="post" onsubmit="return validate()">
            </c:if>
            <table border="1" cellpadding="5" bgcolor=#f2f2f2 style="color:white">
                <caption>
                    <h2>
                        <c:if test="${employee != null}">
                            Edit Employee
                        </c:if>
                        <c:if test="${employee == null}">
                            Add New Employee
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${employee != null}">
                    <input type="hidden" name="id" value="<c:out value='${employee.id}' />"/>
                </c:if>
                <tr>
                    <th bgcolor=#4CAF50>Employee Name: <span class="star" style="color:red; font-size: 30px;">*</span>
                    </th>
                    <td>
                        <input type="text" name="name" id="name" size="45"
                               value="<c:out value='${employee.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th bgcolor=#4CAF50>Employee LastName: <span class="star"
                                                                 style="color:red; font-size: 30px;">*</span></th>
                    <td>
                        <input type="text" name="lastName" id="lastName" size="45"
                               value="<c:out value='${employee.lastName}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th bgcolor=#4CAF50>Employee Email: <span class="star" style="color:red; font-size: 30px;">*</span>
                    </th>
                    <td>
                        <input type="text" name="email" id="email" size="45"
                               value="<c:out value='${employee.email}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th bgcolor=#4CAF50>Phone:</th>
                    <td>
                        <input type="text" name="phone" id="phone" size="15"
                               value="<c:out value='${employee.phone}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th bgcolor=#4CAF50>Employee Age:</th>
                    <td>
                        <input type="text" name="age" id="age" size="45"
                               value="<c:out value='${employee.age}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th bgcolor=#4CAF50>Employee Role: <span class="star" style="color:red; font-size: 30px;">*</span>
                    </th>
                    <td>
                        <select name="role" id="role">
                            <c:forEach items="${roles}" var="role">
                                <option value="${role.id}">${role.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th bgcolor=#4CAF50>Employee ManagerId: <span class="star"
                                                                  style="color:red; font-size: 30px;">*</span></th>
                    <td>
                        <select name="managerId">
                            <c:forEach items="${manager}" var="manage">
                                <option value="${manage.id}">${manage.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th bgcolor=#4CAF50>Employee Address:</th>
                    <td>
                        <input type="text" name="address" id="address" size="15"
                               value="<c:out value='${employee.address}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <c:if test="${employee != null}">
                        <th bgcolor=#4CAF50>Employee active:</th>
                        <td>
                            <input type="checkbox" id="active" name="active" value="true">
                        </td>
                    </c:if>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>