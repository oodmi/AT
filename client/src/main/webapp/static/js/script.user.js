var dialog;
var id;

function getCredentials(){
    return 'Basic ' + btoa($("#username").val() + ":" + $('#user-password').val())
}

$.ajaxSetup({
    crossDomain: true,
    headers: {
        // 'Access-Control-Allow-Origin' : true,
        'Authorization' : getCredentials()
    }
});
function deleteUser(user) {
    if (!confirm("Do you really want to delete this user?")) return;
    $.ajax(url + "/user/" + user.id, {
        method: "DELETE",
        dataType: "json",
        contentType: 'application/json; charset=utf-8'
    }).done(function () {
        $("#tr" + user.id).remove();
    });
}

function addUser(user) {
    $.ajax(url + "/user/", {
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
    $.ajax(url + "/user/" + user.id, {
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
    $.ajax(url + "/user/", {
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


    $("#add-new-user").button().on("click", function () {
        dialog.dialog("open");
        $("#ui-id-1").text('Create new user')
    });

    $("#show").button().on("click", function () {
        getUsers();
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
});