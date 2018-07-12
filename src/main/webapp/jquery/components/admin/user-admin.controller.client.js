(function () {
    var $usernameFld = $('#usernameFld')
    var $passwordFld = $('#passwordFld');
    var $emailFld = $("#emailFld");
    var $firstNameFld = $("#firstNameFld");
    var $lastNameFld = $("#lastNameFld");
    var $roleFld = $("#roleFld");
    var $phoneFld = $("#phoneFld");
    var $dateOfBirthFld = $("#dateOfBirthFld");
    var $createBtn = $('#createBtn');
    var $updateBtn = $('#updateBtn');
    var $userRowTemplate, $tbody;
    var userServiceClient = new UserServiceClient();


    $createBtn.click(createUser);
    $updateBtn.click(updateUser);


    function main() {

        userServiceClient.findAllUsers()
            .then(renderUsers).then(clearUserObject);
    }
    main();

    function getUserObject() {
        var usernameStr = $usernameFld.val();
        var passwordStr = $passwordFld.val();
        var emailStr = $emailFld.val();
        var firstNameStr = $firstNameFld.val();
        var lastNameStr = $lastNameFld.val();
        var roleStr = $roleFld.val();
        var phoneStr = $phoneFld.val();
        var dateOfBirthStr = $dateOfBirthFld.val();

        var userObj = {
            username: usernameStr,
            password: passwordStr,
            email: emailStr,
            firstName: firstNameStr,
            lastName: lastNameStr,
            role: roleStr,
            phone: phoneStr,
            dateOfBirth: dateOfBirthStr
        };

        return userObj;
    }

    function disableInputField(field) {
        document.getElementById(field).disabled = true;
    }

    function enablleInputField(field) {
        document.getElementById(field).disabled = false;
    }

    function putUserInForm(user) {
        $updateBtn.attr('id', user.id);
        $usernameFld.val(user.username);
        disableInputField("usernameFld");
        $passwordFld.val(user.password);
        $emailFld.val(user.email);
        $firstNameFld.val(user.firstName);
        $lastNameFld.val(user.lastName);
        $roleFld.val(user.role);
        $phoneFld.val(user.phone);
        $dateOfBirthFld.val(user.dateOfBirth);

    }


    function clearUserObject() {
        $updateBtn.attr('id', "");
        $usernameFld.val("");
        enablleInputField("usernameFld");
        $passwordFld.val("");
        $emailFld.val("");
        $firstNameFld.val("");
        $lastNameFld.val("");
        $roleFld.val("");
        $phoneFld.val("");
        $dateOfBirthFld.val("");
    }

    function createUser() {

        var userObj = getUserObject();
        var userObjStr = JSON.stringify(userObj);
        userServiceClient
            .createUser(userObjStr)
            .then(function() {
                userServiceClient
                    .findAllUsers()
                    .then(renderUsers);
            });
    }

    function findUserById(event) {
        var $button = $(event.currentTarget);
        var id = $button.attr('id');

        userServiceClient.findUserById(id)
            .then(putUserInForm);
    }

    function deleteUser(event) {
        var $button = $(event.currentTarget);
        var id = $button.attr('id');

        userServiceClient
            .deleteUser(id)
            .then(function () {
                userServiceClient
                    .findAllUsers()
                    .then(renderUsers);
            });
    }

    function selectUser() {

    }


    function updateUser(event) {
        var $button = $(event.currentTarget);
        var id = $button.attr('id');

        var userObj = getUserObject();
        var userObjStr = JSON.stringify(userObj);

        userServiceClient
            .updateUser(id, userObjStr)
            .then(function () {
                userServiceClient
                    .findAllUsers()
                    .then(renderUsers);
            });
    }

    function renderUser(user) {
        var tr = $('<tr>');
        var td = $('<td>');
        td.append(user.username);
        tr.append(td);

        td = $('<td>');
        td.append('******');
        tr.append(td);

        td = $('<td>');
        td.append(user.email);
        tr.append(td);

        td = $('<td>');
        td.append(user.firstName);
        tr.append(td);

        td = $('<td>');
        td.append(user.lastName);
        tr.append(td);

        td = $('<td>');
        td.append(user.role);
        tr.append(td);

        td = $('<td>');
        td.append(user.phone);
        tr.append(td);

        td = $('<td>');
        td.append(user.dateOfBirth);
        tr.append(td);

        td = $('<td>');
        var deleteBtn = $('<button>DELETE</button>');
        deleteBtn.click(deleteUser);
        deleteBtn.attr('id', user.id);
        td.append(deleteBtn);

        var editBtn = $('<button>EDIT</button>');
        editBtn.click(findUserById);
        editBtn.attr('id', user.id);
        td.append(editBtn);

        tr.append(td);

        return tr;

    }

    function renderUsers(users) {

        clearUserObject();
        var tbody = $('tbody');
        tbody.empty();
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var row = renderUser(user);
            row.appendTo(tbody);
        }
    }
})();
