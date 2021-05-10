<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee Management Application</title>
</head>
<body>
<%--<center>
    <h1>Send Email</h1>

</center>--%>
<div align="center">

    <form action="email/insert" method="post">

        <table border="1" cellpadding="5" bgcolor=#f2f2f2 style="color:white">
            <caption style="color: black;">
                <h2>
                    Send New Email
                </h2>
            </caption>
            <%--<tr>--%>
            <%--<th bgcolor=#4CAF50>Person Id:</th>--%>
            <%--<td>--%>
            <%--<input type="number" name="personId" size="45"/>--%>

            <%--</td>--%>
            <%--</tr>--%>
            <tr>
                <th bgcolor=#4CAF50>Receiver :</th>
                 <td>
                     <select name="receiver" id="receiver">
                         <c:forEach items="${receivers}" var="receiver">
                             <option value="${receiver.id}">${receiver.name}</option>
                         </c:forEach>
                     </select>
                     <input type="hidden" id="id" name="id" value="${id}">
                 </td>
               <%-- <td>
                    <input type="text" name="receiver" size="50"
                           value="<c:out value='${employee.email}' />"
                    />

                </td>--%>
            </tr>
            <tr>
                <th bgcolor=#4CAF50>Subject :</th>
                <td>
                    <input type="text" name="subject" size="50"/>

                </td>
            </tr>
            <tr>
                <th bgcolor=#4CAF50>Message :</th>
                <td>
                    <textarea rows="10" cols="39" name="message"></textarea>
                </td>
            </tr>
            <%--<tr>
                <th bgcolor=#4CAF50>Date:</th>
                <td>
                    <input type="date" name="date" size="45"/>
                   &lt;%&ndash; <input type="datetime-local" name="date" size="45"/>&ndash;%&gt;
                </td>
            </tr>
            <tr>--%>
            <td colspan="2" align="center">
                <input type="submit" value="Save"/>
            </td>
            </tr>
        </table>
    </form>

    <table border="1" cellpadding="5" bgcolor=#f2f2f2 style="color:white">
        <caption style="text-align: center;color: black;"><h2>Inbox</h2></caption>
        <tr bgcolor=#4CAF50>
            <th>ID</th>
            <th>Sender Name</th>
            <th>Sender LastName</th>
            <th>Email</th>
            <th>subject</th>
            <th>Message</th>
            <th>Date</th>
        </tr>
        <c:forEach var="mail" items="${inboxList}">
            <tr style="color:black">
                <td><c:out value="${mail.id}"/></td>
                <td><c:out value="${mail.name}"/></td>
                <td><c:out value="${mail.lastName}"/></td>
                <td><c:out value="${mail.email}"/></td>
                <td><c:out value="${mail.subject}"/></td>
                <td style="width: 270px;"><c:out value="${mail.message}"/></td>
                <td><c:out value="${mail.date}"/></td>
            </tr>
        </c:forEach>
    </table>

    <table border="1" cellpadding="5" bgcolor=#f2f2f2 style="color:white">
        <caption style="text-align: center;color: black;"><h2>Sent Box</h2></caption>
        <tr bgcolor=#4CAF50>
            <th>ID</th>
            <th>Receiver Name</th>
            <th>Receiver LastName</th>
            <th>Email</th>
            <th>subject</th>
            <th>Message</th>
            <th>Date</th>
        </tr>
        <c:forEach var="mail" items="${outboxList}">
            <tr style="color:black">
                <td><c:out value="${mail.id}"/></td>
                <td><c:out value="${mail.name}"/></td>
                <td><c:out value="${mail.lastName}"/></td>
                <td><c:out value="${mail.email}"/></td>
                <td><c:out value="${mail.subject}"/></td>
                <td style="width: 270px;"><c:out value="${mail.message}"/></td>
                <td><c:out value="${mail.date}"/></td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>