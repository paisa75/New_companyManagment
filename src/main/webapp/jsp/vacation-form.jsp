<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee Management Application</title>

    <link type="text/css" rel="stylesheet" href="/css/kamadatepicker.min.css">
    <script src="//code.jquery.com/jquery.min.js"></script>
    <script src="/js/kamadatepicker.min.js"></script>
</head>
<body>
<center>
    <h1>vacation Request</h1>
</center>
<div align="center">

    <form action="vacation/insertVacation" method="post">

        <table border="1" cellpadding="5" bgcolor=#f2f2f2 style="color:white">
            <caption>
                <h2>
                    Add New Vacation Request
                </h2>
            </caption>

            <tr>
                <th bgcolor=#4CAF50>Date from :</th>
                <td>
                    <%--<input type="datetime-local" name="from" size="45"/>--%>
                    <input type="text" id="date1" autocomplete="off" name="from" size="45">
                    <input type="hidden" id="id1" name="id" value="${id}">
                </td>
            </tr>
            <tr>
                <th bgcolor=#4CAF50>Date to :</th>
                <td>
                    <input type="text" id="date2" autocomplete="off" name="to" size="45">
                    <input type="hidden" id="id2" name="id" value="${id}">
                </td>
            </tr>
            <tr>
                <th bgcolor=#4CAF50>Description :</th>
                <td>
                    <input type="text" name="description" size="45"/>
                </td>
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
<script>
    kamaDatepicker('date1', {buttonsColor: "red"});
    kamaDatepicker('date2', {buttonsColor: "red"});

    var customOptions = {
        placeholder: "روز / ماه / سال"
        , twodigit: false
        , closeAfterSelect: false
        , nextButtonIcon: "fa fa-arrow-circle-right"
        , previousButtonIcon: "fa fa-arrow-circle-left"
        , buttonsColor: "blue"
        , forceFarsiDigits: true
        , markToday: true
        , markHolidays: true
        , highlightSelectedDay: true
        , sync: true
        , gotoToday: true
    }
</script>
</html>