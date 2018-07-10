(function () {
    var usernameFld = $('#usernameFld')
    var passwordFld = $('#passwordFld');
    var emailFld = $("#emailFld");
    var firstNameFld = $("#firstNameFld");
    var lastNameFld = $("#lastNameFld");
    var roleFld = $("#roleFld");
    var phoneFld = $("#phoneFld");
    var dateOfBirthFld = $("#dateOfBirthFld");
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    $(main);

    $createBtn.click(createUser());
    function main() {
        //TODO
    }

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

        fetch('/create', {
            method: 'post',
            body: userObjStr,
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    function findAllUsers() {
    }

    function findUserById() {
    }
    function deleteUser() {

    }
    function selectUser() {

    }
    function updateUser() {

    }
    function renderUser(user) {

    }
    function renderUsers(users) {

    }

})();
