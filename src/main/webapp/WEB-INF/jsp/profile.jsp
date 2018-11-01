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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        .prokrutka {
            height: 500px;
            width: 90%;
            background: #fff;
            border: 1px solid #C1C1C1;
            overflow-x: scroll;
            overflow-y: scroll;
        }

        table {
            width: 100%;
        }
    </style>
</head>
<body>

<h3>Search</h3>
<form action="${pageContext.request.contextPath}/page?idCurrentFolder=${requestScope.idCurrentFolder}" method="post">
    <input type="text" size="50" name="searchTerm" value="${requestScope.searchTerm}">
    type:
    <select name="type">
        <optgroup label="Select Type">
            <option value=""></option>
            <c:forEach items="${requestScope.types}" var="type">
            <option value="${type}">${type}</option>
            </c:forEach>
    </select>
    <br>
    <input type="submit" class="submit" value="Search"><br>
</form>

<c:if test="${not empty requestScope.link}">
    <p><a href="${requestScope.link}">${requestScope.link}</a></p>
</c:if>
<table>
    <tr>
        <td>
            <c:if test="${not empty requestScope.idLastFolder}">
                <a href="${pageContext.request.contextPath}/page?id=${requestScope.idLastFolder}"> <span class="glyphicon glyphicon-circle-arrow-left"></span>Back</a>
            </c:if>
            <div class="prokrutka">
                <table>
                    <c:forEach items="${requestScope.folders}" var="folder">
                        <tr>
                            <td>
                                <span class="glyphicon glyphicon-folder-open"></span>
                                <a href="${pageContext.request.contextPath}/page?id=${folder.id}"> ${folder.name}</a>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/page?idCurrentFolder=${requestScope.idCurrentFolder}&id=${folder.id}"
                                      method="post" style="display: inline;scroll-padding-left:150px;">
                                    <input type="submit" name="link" value="Share link">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:forEach items="${requestScope.files}" var="file">
                        <tr>
                            <td>
                                <span class="glyphicon glyphicon-file"></span>
                                <a href="${pageContext.request.contextPath}/page?id=${file.id}">${file.name}</a>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/page?idCurrentFolder=${requestScope.idCurrentFolder}&id=${file.id}"
                                      method="post" style="display: inline;scroll-padding-left:150px;">
                                    <input type="submit" name="link" value="Share link">
                                </form>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </td>
    </tr>
</table>
<h3>Upload</h3>
<form action="${pageContext.request.contextPath}/page?idCurrentFolder=${requestScope.idCurrentFolder}"
      enctype="multipart/form-data" method="post">
    <input type="file" required name="file">
    <br>
    <input type="submit" class="submit" value="Upload"><br>
</form>
</body>
</html>
