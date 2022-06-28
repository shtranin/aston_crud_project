<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">
        <div>
            <a class="navbar-brand"> Users and Groups Manager </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Users</a></li>
        </ul>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/groups"
                   class="nav-link">Groups</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">


    <div class="container">
        <h3 class="text-center">Available Groups to Subscribe</h3>
        <hr>


        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
<%--                <th>User count</th>--%>

            </tr>
            </thead>
            <tbody>

            <c:forEach var="group" items="${listGroup}">

                <tr>
                    <td><c:out value="${group.id}" /></td>
                    <td><c:out value="${group.name}" /></td>
<%--                    <td><c:out value="${group.userCount}" /></td>--%>

                    <td> <a href="subscribe_group?group_id=<c:out value='${group.id}'/>&user_id=<c:out value='${userId}' />">Subscribe</a></td>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </div>
</div>
</body>
</html>
