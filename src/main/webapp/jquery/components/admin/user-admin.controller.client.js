(function () {
    var usernameFld = $('#usernameFld')
    var passwordFld = $('#passwordFld');
    var emailFld = $("#emailFld");
    var firstNameFld = $("#firstNameFld");
    var lastNameFld = $("#lastNameFld");
    var roleFld = $("#roleFld");
    var phoneFld = $("#phoneFld");
    var dateOfBirthFld = $("#dateOfBirthFld");
    var $removeBtn, $editBtn;
    var createBtn = $('#createBtn');
    var $userRowTemplate, $tbody;
    // var userService = new AdminUserServiceClient();
    $(main);

    createBtn.click(createUser);
    // $removeBtn.click(deleteUser);
    // $editBtn.click(updateUser);


    function main() {
        //TODO
        // findAllUsers()
        //     .then(renderUser);
    }
    main();

    function createUser() {
        var usernameStr = usernameFld.val();
        var passwordStr = passwordFld.val();
        var emailStr = emailFld.val();
        var firstNameStr = firstNameFld.val();
        var lastNameStr = lastNameFld.val();
        var roleStr = roleFld.val();
        var phoneStr = phoneFld.val();
        var dateOfBirthStr = dateOfBirthFld.val();

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

        var userObjStr = JSON.stringify(userObj);
        alert("Successfully created User");
        fetch('/api/user', {
            method: 'Post',
            body: userObjStr,
            headers: {
                'Content-Type': 'application/json'
            }
        });

    }

    function findAllUsers() {
        return fetch('/getAllUsers')
        .then(function(response) {
            return response.json();
        })
    }

    function findUserById() {
    }

    function deleteUser() {

    }

    function selectUser() {

    }

    function updateUser() {

    }

    function renderUser(users) {
        console.log(users);
    }

    function renderUsers(users) {

    }
})();
