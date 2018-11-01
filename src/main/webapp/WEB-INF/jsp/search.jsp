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
    <style>
        .prokrutka {
            height: 500px;
            width: 50%;
            background: #fff;
            border: 1px solid #C1C1C1;
            overflow-x: scroll;
            overflow-y: scroll;
        }
    </style>
</head>
<body>
<h3>Search</h3>
<form action="${pageContext.request.contextPath}/page?idCurrentFolder=${requestScope.id}" method="post">
    <input type="text" size="50" name="searchTerm" value="${requestScope.searchTerm}">
    type:
    <select name="type">
        <optgroup label="Select Type">
            <option value=""></option>
            <c:forEach items="${requestScope.types}" var="type">
            <option value="${type}">${type}</option>
            </c:forEach>
    </select><br>
    <input type="submit" class="submit" value="Search"><br>
</form>

<c:if test="${not empty requestScope.id}">
    <a href="${pageContext.request.contextPath}/page?id=${requestScope.id}"> Back</a>
</c:if>
<h3>Result Search</h3>
<c:if test="${not empty requestScope.files}">
<div class="prokrutka">
    <c:forEach items="${requestScope.files}" var="file">
    <p><a href="${pageContext.request.contextPath}/page?id=${file.id}">${file.name}</a></p>
    </c:forEach>
</div>
</c:if>
<c:if test="${not empty requestScope.error}">
    <p style="color: darkred;">${requestScope.error}</p>
</c:if>
</body>
</html>
