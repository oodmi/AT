var dialog;
var bookUpdate = {};
var limit = 0;

function updateSort() {
    $('#table_with_books').trigger("update");
    $('#table_with_books').trigger("sorton", [[[1, 0]]]);//$('#table_with_books').get(0).config.sortList);
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
            addRowToTable(book);
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
            updateRowInTable(book);
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
    } else if (currentUser.name == book.owner) {
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
});