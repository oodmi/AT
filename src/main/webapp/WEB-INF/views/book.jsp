<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
    <script type="text/javascript"
            src="../../static/jquery/js/jquery-2.2.1.min.js"></script>
    <script type="text/javascript"
            src="../../static/jquery/js/jquery.tablesorter.min.js"></script>
    <script type="text/javascript" src="../../static/jquery/js/jquery-ui.js"></script>
    <script type="text/javascript"
            src="../../static/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../static/js/script.book.js"></script>

    <link rel="stylesheet" href="../../static/bootstrap-3.3.6-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="../../static/jquery/css/jquery-ui.css"/>
    <link rel="stylesheet" href="../../static/jquery/themes/blue/style.css">
    <link rel="stylesheet" href="../../static/css/book-style.css"/>

    <link rel="shortcut icon" href="../../static/images/favicon.ico" type="image/x-icon">

    <script>
        var currentUser = {};
        currentUser.name = '<c:out value="${pageContext.request.userPrincipal.name}"/>';
    </script>

    <title>Books</title>

</head>
<body id="body">
<div class="container">
    <div class="col-md-2"></div>
    <div class="col-md-8">
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
        <div id="dialog-form" title="Create new book">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="isn">ISN:</label>
                    <div class="col-sm-10">
                        <input type="number" min="1" class="form-control" id="isn" placeholder="Enter isn"
                               required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="name">Name:</label>
                    <div class="col-sm-10">
                        <input type="text" minlength="3" maxlength="30" class="form-control" id="name"
                               placeholder="Enter name" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="author">Author:</label>
                    <div class="col-sm-10">
                        <input type="text" minlength="3" maxlength="30" class="form-control" id="author"
                               placeholder="Enter author" required>
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
            <table class="tablesorter table table-condensed" id="table_with_books">
                <thead>
                <tr>
                    <th>ISN</th>
                    <th>Author</th>
                    <th>Name</th>
                    <th>Owner</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <div class="btn-group btn-group-justified">
            <button id="add-new-book" type="button" class="btn btn-default">Add new book
            </button>
            <button id="show-more-books" type="button" class="btn btn-default">Show more books
            </button>
        </div>
    </div>
    <div class="col-md-2"></div>
</div>

</body>
</html>
