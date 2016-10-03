<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="true" %>
<html>
<head>
    <script type="text/javascript"
            src="../../static/jquery/js/jquery-2.2.1.min.js"></script>
    <script type="text/javascript" src="../../static/jquery/js/jquery-ui.js"></script>
    <script type="text/javascript"
            src="../../static/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../static/js/script.user.js"></script>

    <link rel="stylesheet" href="../../static/bootstrap-3.3.6-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="../../static/jquery/css/jquery-ui.css"/>
    <link rel="stylesheet" href="../../static/css/user-style.css"/>

    <link rel="shortcut icon" href="../../static/images/favicon.ico" type="image/x-icon">

    <title>Users</title>

   </head>
<body id="body">
<div class="container">
    <div class="col-md-3"></div>
    <div class="col-md-6">
        <div class="panel panel-default">
            <div class="panel-body">
                <div id="head-div">
                    <a href="${pageContext.request.contextPath}/view/user/"
                       style="color: #3897f0; text-decoration: none">
                        Users</a>
                    <a href="${pageContext.request.contextPath}/view/book/"
                       style="color: #3897f0; text-decoration: none">
                        Books</a>
                </div>
            </div>
        </div>
        <div id="dialog-form">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="login">Login:</label>
                    <div class="col-sm-10">
                        <input type="text" minlength="3" maxlength="30" class="form-control" id="login"
                               placeholder="Enter login"
                               required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="password">Password:</label>
                    <div class="col-sm-10">
                        <input type="password" minlength="3" maxlength="30" class="form-control" id="password"
                               placeholder="Enter password" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="btn-group btn-group-justified">
                        <button id="close-button" type="button" class="btn btn-default">Close
                        </button>
                        <button id="save-button" type="submit" class="btn btn-default">Save</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="panel panel-default">
            <table class="table table-condensed" id="table_with_users">
                <thead>
                <tr>
                    <th data-type="number">Login</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="tbody_with_users"></tbody>
            </table>
        </div>
        <div class="btn-group btn-group-justified">
            <button id="add-new-user" type="button" class="btn btn-default">Add new user
            </button>
        </div>
    </div>
    <div class="col-md-3"></div>
</div>

</body>
</html>
