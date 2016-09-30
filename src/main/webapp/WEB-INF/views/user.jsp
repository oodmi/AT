<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="../../static/jquery/js/jquery-2.2.1.min.js"></script>
    <script type="text/javascript" src="../../static/jquery/js/jquery-ui.js"></script>
    <script type="text/javascript" src="../../static/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="../../static/bootstrap-3.3.6-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="../../static/jquery/css/jquery-ui.css"/>

    <link rel="stylesheet" href="../../static/css/styles.css"/>
    <link rel="shortcut icon" href="../../static/images/favicon.ico" type="image/x-icon">

    <title>Users</title>

    <style>
        a:hover {
            text-decoration: none;
            cursor: pointer;
            color: blue;
        }

    </style>

    <script type="text/javascript">

        var dialog;
        var id;

        function deleteUser(user) {
            if (!confirm("Do you really want to delete this user?")) return;
            $.ajax("/user/" + user.id, {
                method: "DELETE",
                dataType: "json",
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                $("#tr" + user.id).remove();
            });
        }

        function addUser(user) {
            $.ajax("/user/", {
                method: "POST",
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(user)
            }).done(function (valid) {
                if (valid) {
                    dialog.dialog("close");
                    addRowToTable(user)
                }
                else {
                    dialog.dialog("close");
                    alert("Error! User with login = " + user.login + " already exist")
                }
            })
        }

        function updateUser(user) {
            $.ajax("/user/" + user.id, {
                method: "PUT",
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(user)
            }).done(function (valid) {
                if (valid) {
                    dialog.dialog("close");
                    updateRowInTable(user)
                }
                else {
                    dialog.dialog("close");
                    alert("Error! User with login = " + user.login + " already exist")
                }
            })
        }

        function getUsers() {
            $.ajax("/user/", {
                method: "GET",
                dataType: "json",
                contentType: 'application/json; charset=utf-8'
            }).done(function (data) {
                $(data).each(function (i, user) {
                    addRowToTable(user)
                });
            });
        }

        function addRowToTable(user) {
            var table = document.getElementById("table_with_users");
            var tbody = table.lastElementChild;
            var tr = document.createElement('tr');
            tr.id = "tr" + user.id;
            tbody.appendChild(tr);

            var td1 = document.createElement('td');
            var td2 = document.createElement('td');

            tr.appendChild(td1);
            tr.appendChild(td2);

            td1.innerHTML = "<a id='a" + user.id + "'>" + user.login + "</a>";
            $("#a" + user.id).on("click", function (event) {
                event.preventDefault();
                dialog.dialog("open");
                $("#login").val(user.login);
                id = user.id;
                $("#ui-id-1").text('Update user');
            });

            var button = document.createElement('button');
            button.classList.add("btn", "btn-default");
            button.innerHTML = "delete";
            button.onclick = function () {
                deleteUser(user)
            };
            td2.appendChild(button);
        }

        function updateRowInTable(user) {
            $("#tr" + user.id).remove();
            addRowToTable(user);
        }

        $(document).ready(function () {
            dialog = $("#dialog-form").dialog({
                autoOpen: false,
                height: 280,
                width: 400,
                modal: true,
                close: function () {
                    dialog.find("form")[0].reset();
                },
                open: function () {
                    $(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar-close").remove();
                }
            });
            var login = $("#login"),
                    password = $("#password");
            getUsers();

            $("#add-new-user").button().on("click", function () {
                dialog.dialog("open");
                $("#ui-id-1").text('Create new user')
            });

            $("#close-button").button().on("click", function () {
                dialog.dialog("close");
            });

            dialog.find("form").on("submit", function (event) {
                event.preventDefault();
                var user = {};
                user.id = id;
                user.login = login.val();
                user.password = password.val();
                if ($("#ui-id-1").text() == 'Update user') {
                    updateUser(user);
                }
                else {
                    addUser(user);
                }
            });
        })

    </script>
</head>
<body style="background: #f8f8f8">
<div class="container">
    <div class="col-md-2"></div>
    <div class="col-md-8" style="font-size: 15px">
        <div class="panel panel-default" style=" margin-top: 20pt">
            <div class="panel-body">
                <div style=" text-align: center ;">
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
                        <button id="close-button" type="button" class="btn btn-default"
                                style="margin-left: 10%; width: 40%">Close
                        </button>
                        <button type="submit" class="btn btn-default" style="width: 40%">Save</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="panel panel-default" style=" margin-top: 20pt">
            <table class="table table-condensed tablesorter" id="table_with_users">
                <thead>
                <tr>
                    <th data-type="number">Login</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="tbody_with_users"></tbody>
            </table>
        </div>
        <div class="btn-group btn-group-justified" style="margin-bottom: 20pt ; margin-top: 20pt">
            <button id="add-new-user" type="button" class="btn btn-default"
                    style="width: 50%">Add new user
            </button>
        </div>
    </div>
    <div class="col-md-2"></div>
</div>

</body>
</html>
