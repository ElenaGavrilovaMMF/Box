<%--
  Created by IntelliJ IDEA.
  User: HaurylavaA
  Date: 18.10.2018
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SearchResult</title>
</head>
<body>
<h3>Search</h3>
<form action="${pageContext.request.contextPath}/page?idCurrentFolder=${requestScope.id}" method="post">
    <input type="text" size="50" name="searchTerm" value="${requestScope.searchTerm}">
    <select name="type">
        <optgroup label="Select Type">
            <option value=""></option>
            <c:forEach items="${requestScope.types}" var="type">
            <option value="${type}">${type}</option>
            </c:forEach>
    </select><br>
    <input type="submit" class="submit" value="Search"><br>
</form>

<c:if test="${not empty id}">
    <a href="${pageContext.request.contextPath}/page?id=${id}"> Назад</a>
</c:if>
<h3>Result Search</h3>
<c:forEach items="${files}" var="file">
    <p><a href="${pageContext.request.contextPath}/page?id=${file.id}">${file.name}</a></p>
</c:forEach>
</body>
</html>
