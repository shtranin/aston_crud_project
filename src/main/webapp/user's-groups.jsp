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


    </nav>
</header>
<br>

<div class="row">

    <div class="container">
        <h3 class="text-center">Groups of user</h3>
        <hr>
        <div class="container text-left">

            <a href="list" class="btn btn-success">Back to Users list</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Group name</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="group" items="${userGroups}">

                <tr>
                    <td><c:out value="${group.name}" /></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
