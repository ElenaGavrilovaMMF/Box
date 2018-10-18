<%--
  Created by IntelliJ IDEA.
  User: HaurylavaA
  Date: 12.10.2018
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<h3>Search</h3>
<form action="${pageContext.request.contextPath}/page?idCurrentFolder=${requestScope.idCurrentFolder}" method="post">
    <input type="text" required size="50" name="searchTerm" value="${requestScope.searchTerm}">
    <select name="type">
        <optgroup label="Select Type">
            <option value=""></option>
        <c:forEach items="${requestScope.types}" var="type">
            <option value="${type}">${type}</option>
        </c:forEach>
    </select><br>
    <input type="submit" class="submit" value="Search"><br>
</form>

<c:if test="${not empty requestScope.idLastFolder}">
    <a href="${pageContext.request.contextPath}/page?id=${requestScope.idLastFolder}"> Назад</a>
</c:if>

<h3>Folders</h3>
<c:forEach items="${requestScope.folders}" var="folder">
    <p><a href="${pageContext.request.contextPath}/page?id=${folder.id}"> ${folder.name}</a></p>
</c:forEach>

<h3>Files</h3>
<c:forEach items="${requestScope.files}" var="file">
    <p><a href="${pageContext.request.contextPath}/page?id=${file.id}">${file.name}</a></p>
</c:forEach>

<h3>Upload</h3>
<form action="${pageContext.request.contextPath}/page?idCurrentFolder=${requestScope.idCurrentFolder}"
      enctype="multipart/form-data" method="post">
    <input type="file" required name="file">
    <br>
    <input type="submit" class="submit" value="Upload"><br>
</form>

</body>
</html>
