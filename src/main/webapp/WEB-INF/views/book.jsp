<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="../../static/jquery/js/jquery-2.2.1.min.js"></script>
    <%--<script type="text/javascript" src="../../static/jquery/js/jquery.tablesorter.js"></script>--%>
    <script type="text/javascript" src="../../static/jquery/js/jquery.tablesorter.min.js"></script>
    <script type="text/javascript" src="../../static/jquery/js/jquery-ui.js"></script>
    <script type="text/javascript" src="../../static/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="../../static/bootstrap-3.3.6-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="../../static/jquery/css/jquery-ui.css"/>

    <link rel="stylesheet" href="../../static/css/styles.css"/>
    <link rel="shortcut icon" href="../../static/images/favicon.ico" type="image/x-icon">

    <title>Books</title>

    <style>
        .thsort {
            cursor: pointer;
        }

        .thsort:hover {
            color: blue;
        }
    </style>

    <script type="text/javascript">

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


            td1.innerHTML = book.isn;
            td2.innerHTML = book.name;
            td3.innerHTML = book.author;
            td4.innerHTML = book.owner;

            var button = document.createElement('button');
            button.classList.add("btn", "btn-default");
            button.innerHTML = "delete";
            button.onclick = function () {
                deleteBook(book.isn)
            };
            td5.appendChild(button);
        }

        $(document).ready(function () {
            $.ajax("/book/", {
                method: "GET",
                dataType: "json",
                contentType: 'application/json; charset=utf-8'
            }).done(function (data) {
                $(data).each(function (i, book) {
                    addRowToTable(book)
                });
            });

//            $("#table_with_books").tablesorter({sortList: [[0, 0], [1, 0]]});

        });

        $(function () {
            var form,
                    dialog,
                    isn = $("#isn"),
                    name = $("#name"),
                    author = $("#author"),
                    allFields = $( [] ).add( isn ).add( name ).add( author );

            function addBook() {
                var book = {};
                console.log(isn.val());
                book.isn = isn.val();
                book.name = name.val();
                book.author = author.val();
                book.owner = null;
                $.ajax("/book/", {
                    method: "POST",
                    dataType: "json",
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(book)
                }).done(function (valid) {
                    if (valid) {
                        dialog.dialog( "close" );
                       addRowToTable(book)
                    }
                    else{
                        dialog.dialog( "close" );
                        alert("Book with ISN = " + book.isn + " exist yet")
                    }
                })
            }


            dialog = $("#dialog-form").dialog({
                autoOpen: false,
                height: 400,
                width: 350,
                modal: true,
                buttons: {
                    "Create a book": addBook,
                    Cancel: function () {
                        dialog.dialog("close");
                    }
                },
                close: function () {
                    form[0].reset();
                }
            });

            form = dialog.find("form").on("submit", function (event) {
                event.preventDefault();
                addBook();
            });

            $("#add-new-book").button().on("click", function () {
                dialog.dialog("open");
            });
        })

    </script>
</head>
<body style="background: #f8f8f8">
<div class="container">
    <div class="col-md-2"></div>
    <div class="col-md-8" style="font-size: 15px">
        <div class="panel panel-default" style=" margin-top: 60pt">
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
                        <input type="number" min="1" value="1" class="form-control" id="isn" placeholder="Enter isn" required="true">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="name">Name:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" placeholder="Enter name" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="author">Author:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="author" placeholder="Enter author" required>
                    </div>
                </div>
                <%--<div class="form-group">--%>
                    <%--<div class="col-sm-offset-2 col-sm-10">--%>
                        <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </form>
        </div>
        <div class="panel panel-default" style=" margin-top: 10pt">
            <table class="table table-condensed tablesorter" id="table_with_books">
                <thead>
                <tr>
                    <th data-type="number">ISN</th>
                    <th data-type="string" class="thsort">Name</th>
                    <th data-type="string" class="thsort">Author</th>
                    <th data-type="string">Owner</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="tbody_with_books"></tbody>
            </table>
        </div>
        <div class="btn-group btn-group-justified">
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
