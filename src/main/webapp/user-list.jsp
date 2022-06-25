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
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/create_user" class="btn btn-success">Add
                New User</a>
        </div>

        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Groups</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="user" items="${listUser}">

                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.email}" /></td>
                    <td><a href="user_groups?id=<c:out value='${user.id}' />&name=<c:out value='${user.name}'/>">All groups of User</a></td>
                    <td><a href="edit_user?id=<c:out value='${user.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete_user?id=<c:out value='${user.id}' />">Delete</a>
                        <a href="subscribe_menu?id=<c:out value='${user.id}' />">Subscribe at group</a></td>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </div>
</div>
</body>
</html>
