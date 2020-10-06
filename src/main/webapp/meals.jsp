<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <jsp:useBean id="mealsToList" scope="request" type="java.util.List"/>
    <c:forEach var="mealTo" items="${mealsToList}">
        <tr style="background-color:${mealTo.excess ? 'red' : 'greenyellow'}">
            <td>${mealTo.dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td>
                <a href="edit.jsp" id="${mealTo.id}">edit</a>
                <a href="meals" id="${mealTo.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="edit.jsp">Add meal</a>
</body>
</html>
