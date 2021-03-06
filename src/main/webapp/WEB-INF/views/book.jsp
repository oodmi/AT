<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript"
            src="../../static/jquery/js/jquery-2.2.1.min.js"></script>
    <%--<script type="text/javascript" src="../../static/jquery/js/jquery.tablesorter.js"></script>--%>
    <script type="text/javascript"
            src="../../static/jquery/js/jquery.tablesorter.min.js"></script>
    <%--<script type="text/javascript" src="../../static/jquery/js/jquery-1.11.1.min.js"></script>--%>

    <script type="text/javascript" src="../../static/jquery/js/jquery-ui.js"></script>
    <script type="text/javascript"
            src="../../static/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="../../static/bootstrap-3.3.6-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="../../static/jquery/css/jquery-ui.css"/>
    <link rel="stylesheet" href="../../static/jquery/themes/blue/style.css">

    <link rel="stylesheet" href="../../static/css/styles.css"/>

    <link rel="shortcut icon" href="../../static/images/favicon.ico" type="image/x-icon">

    <title>Books</title>

    <style>
        a:hover {
            text-decoration: none;
            cursor: pointer;
            color: blue;
        }

        .btn {
            font-size: 8pt;
        }

        #body {
            background: #f8f8f8;
            font-size: 8pt;
        }
    </style>

    <script type="text/javascript">

        var dialog;
        var bookUpdate = {};
        var limit = 0;
        var currentUser = {};
        currentUser.id = 1;

        function updateSort() {
            $('#table_with_books').trigger("update");
            $('#table_with_books').trigger("sorton", [[[1, 0]]]);
        }

        function deleteBook(isn) {
            if (!confirm("Do you really want to delete this book?")) return;
            $.ajax("/book/" + isn, {
                method: "DELETE",
                dataType: "json",
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                $("#tr" + isn).remove();
            });
        }

        function addBook(book) {
            $.ajax("/book/", {
                method: "POST",
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(book)
            }).done(function (valid) {
                if (valid) {
                    dialog.dialog("close");
                    addRowToTable(book)
                    updateSort();
                }
                else {
                    dialog.dialog("close");
                    alert("Error! Book with ISN = " + book.isn + " already exist")
                }
            })
        }

        function updateBook(book) {
            $.ajax("/book/" + book.isn, {
                method: "PUT",
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(book)
            }).done(function (valid) {
                if (valid) {
                    dialog.dialog("close");
                    updateRowInTable(book)
                    updateSort();
                }
                else {
                    dialog.dialog("close");
                    alert("Error! Book with ISN = " + book.isn + " already exist")
                }
            })
        }

        function getBooks(offset, count) {
            $.ajax("/book/limit/" + offset + "/" + count + "/", {
                method: "GET",
                dataType: "json",
                contentType: 'application/json; charset=utf-8'
            }).done(function (data) {
                limit = limit + data.length;
                $(data).each(function (i, book) {
                    addRowToTable(book);
                });
                updateSort();
            });
        }

        function takeBook(isn) {
            $.ajax("/book/take/" + isn, {
                method: "PUT",
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                var buttonReturn = document.createElement('button');
                buttonReturn.classList.add("btn", "btn-default");
                buttonReturn.innerHTML = "return";
                buttonReturn.onclick = function () {
                    returnBook(isn)
                };
                $("#tr" + isn).find("td:nth-child(4)").html(buttonReturn);
            });
        }

        function returnBook(isn) {
            $.ajax("/book/return/" + isn, {
                method: "PUT",
                contentType: 'application/json; charset=utf-8'
            }).done(function () {
                var buttonTake = document.createElement('button');
                buttonTake.classList.add("btn", "btn-default");
                buttonTake.innerHTML = "take";
                buttonTake.onclick = function () {
                    takeBook(isn)
                };
                $("#tr" + isn).find("td:nth-child(4)").html(buttonTake);
            });
        }

        function addRowToTable(book) {
            var table = document.getElementById("table_with_books");
            var tbody = table.lastElementChild;
            var tr = document.createElement('tr');
            tr.id = "tr" + book.isn;
            tbody.appendChild(tr);

            var td1 = document.createElement('td');
            var td2 = document.createElement('td');
            var td3 = document.createElement('td');
            var td4 = document.createElement('td');
            var td5 = document.createElement('td');

            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(td5);

            td1.innerHTML = "<a id='a" + book.isn + "'>" + book.isn + "</a>";
            $("#a" + book.isn).on("click", function (event) {
                event.preventDefault();
                dialog.dialog("open");
                bookUpdate.isn = book.isn;
                bookUpdate.ownerId = book.ownerId;
                bookUpdate.owner = book.owner;
                $("#isn").val(book.isn).disabled = true;
                document.getElementById("isn").disabled = true;
                $("#name").val(book.name);
                $("#author").val(book.author);
                $("#ui-id-1").text('Update book');
            });
            td2.innerHTML = book.author;
            td3.innerHTML = book.name;

            if (book.ownerId == 0) {
                var buttonTake = document.createElement('button');
                buttonTake.classList.add("btn", "btn-default");
                buttonTake.innerHTML = "take";
                buttonTake.onclick = function () {
                    takeBook(book.isn)
                };
                td4.appendChild(buttonTake);
            } else if (currentUser.id == book.ownerId) {
                var buttonReturn = document.createElement('button');
                buttonReturn.classList.add("btn", "btn-default");
                buttonReturn.innerHTML = "return";
                buttonReturn.onclick = function () {
                    returnBook(book.isn)
                };
                td4.appendChild(buttonReturn);
            } else {
                td4.innerHTML = book.owner;
            }

            var button = document.createElement('button');
            button.classList.add("btn", "btn-default");
            button.innerHTML = "delete";
            button.onclick = function () {
                deleteBook(book.isn)
            };
            td5.appendChild(button);
        }

        function updateRowInTable(book) {
            $("#tr" + book.isn).remove();
            addRowToTable(book);
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
            var name = $("#name"),
                    author = $("#author"),
                    isn = $("#isn");

            getBooks(limit, 5);

            $("#show-more-books").button().on("click", function () {
                getBooks(limit, 5);
            });

            $("#add-new-book").button().on("click", function () {
                document.getElementById("isn").disabled = false;
                dialog.dialog("open");
                $("#ui-id-1").text('Create new book')
            });

            $("#close-button").button().on("click", function () {
                dialog.dialog("close");
            });

            dialog.find("form").on("submit", function (event) {
                event.preventDefault();
                bookUpdate.name = name.val();
                bookUpdate.author = author.val();
                bookUpdate.isn = isn.val();
                if ($("#ui-id-1").text() == 'Update book') {
                    updateBook(bookUpdate);
                    bookUpdate = {};
                }
                else {
                    bookUpdate.ownerId = 0;
                    bookUpdate.owner = null;
                    bookUpdate.owner = null;
                    addBook(bookUpdate);
                    bookUpdate = {};
                }
            });
            $("#table_with_books").tablesorter({
//                sortList: [[1, 0]],
                headers: {
                    0: {
                        sorter: false
                    },
                    3: {
                        sorter: false
                    },
                    4: {
                        sorter: false
                    }
                }
            });
        })

    </script>
</head>
<body id="body">
<div class="container">
    <div class="col-md-2"></div>
    <div class="col-md-8">
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
                        <button id="close-button" type="button" class="btn btn-default"
                                style="margin-left: 10%; width: 40%">Close
                        </button>
                        <button type="submit" class="btn btn-default" style="width: 40%">Save</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="panel panel-default" style=" margin-top: 20pt">
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
        <div class="btn-group btn-group-justified" style="margin-bottom: 20pt ; margin-top: 20pt">
            <button id="add-new-book" type="button" class="btn btn-default"
                    style="width: 50%">Add new book
            </button>
            <button id="show-more-books" type="button" class="btn btn-default"
                    style="width: 50%">Show more books
            </button>
        </div>
    </div>
    <div class="col-md-2"></div>
</div>

</body>
</html>
