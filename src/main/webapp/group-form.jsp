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
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">

                <form action="insert_group" method="post">


                    <caption>
                        <h2>
                                Add New Group
                        </h2>
                    </caption>


                    <fieldset class="form-group">
                        <label>Group Name</label> <input type="text"
                                                        value="<c:out value='${group.name}' />" class="form-control"
                                                        name="name" required="required">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Group Type(Public/Private/Channel)</label> <input type="text"
                                                         value="<c:out value='${group.name}' />" class="form-control"
                                                         name="type" required="required">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Parameter (Theme/Max_members/Author)</label> <input type="text"
                                                                                 value="<c:out value='${group.name}' />" class="form-control"
                                                                                 name="param" required="required">
                    </fieldset>


                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
