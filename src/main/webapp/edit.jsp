<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${param.get(\"id\").equals(\"-1\")}">
        <title>Add</title>
    </c:if>
    <c:if test="${!param.get(\"id\").equals(\"-1\")}">
        <title>Edit</title>
    </c:if>

</head>
<body>
<h3><a href="index.html">Home</a></h3>
<form action="meals" method="POST" >
    <input type="hidden" name="id" value="${param.get("id")}">
    <label for="dateTime">DateTime:</label>
    <input type="datetime-local" name="dateTime" id="dateTime" value="${param.get("datetime")}">
    <br>
    <br>
    <label  for="description">Description:</label>
    <input type="text" name="description" id="description" value="${param.get("description")}">
    <br>
    <br>
    <label for="calories">Calories:</label>
    <input type="number" name="calories" id="calories" min="0" value=${param.get("calories")}>
    <br>
    <br>
    <c:if test="${param.get(\"id\").equals(\"-1\")}">
        <input type="submit" value="Add">
    </c:if>
    <c:if test="${!param.get(\"id\").equals(\"-1\")}">
        <input type="submit" value="Save">
    </c:if>
    <a href="meals">
        <input type="button" value="Cancel">
    </a>
</form >
</body>
</html>
