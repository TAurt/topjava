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
<table style="background-color: white"
        border="1">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <jsp:useBean id="mealsToList" scope="request" type="java.util.List"/>
    <c:forEach var="mealTo" items="${mealsToList}">
        <tr style="color:${mealTo.excess ? 'red' : 'darkgreen'}">
            <td>${mealTo.dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td>
                <a href="edit.jsp?action=edit&id=<c:out value="${mealTo.id}"/>
                    &description=<c:out value="${mealTo.description}"/>
                    &calories=<c:out value="${mealTo.calories}"/>
                    &datetime=<c:out value="${mealTo.dateTime}"/>" style="color: blue">edit</a>
                <a href="meals?action=delete&id=<c:out value="${mealTo.id}"/>" style="color: darkred">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="edit.jsp?action=add&id=-1">Add meal</a>
</body>
</html>
