(function () {

    var $username, $password, $email,
        $firstName, $lastName, $role,
        $phoneNumber, $dateOfBirth,
        $updateBtn, $logoutBtn;

    var userServiceClient = new UserServiceClient();
    var currentUser = null;

    function init() {

        $username = $("#username");
        $password = $("#password");
        $email = $("#email");
        $firstName = $("#firstName");
        $lastName = $("#lastName");
        $role = $("#role");
        $phoneNumber = $("#phoneNumber");
        $dateOfBirth = $("#dateOfBirth");

        $updateBtn = $("#updateBtn");
        $logoutBtn = $("#logoutBtn")

        $updateBtn.click(updateUser);
        $logoutBtn.click(logout);

        profile()
            .then(renderUser);
    }
    init();

    function updateUser() {
        var user = {
            password: $password.val(),
            email: $email.val(),
            firstName: $firstName.val(),
            lastName: $lastName.val(),
            role: $role.val(),
            phone: $phoneNumber.val(),
            dateOfBirth: $dateOfBirth.val()

        };

        userServiceClient.updateUserProfile(user)
            .then(updateSucceeded());
    }

    function updateSucceeded() {
        alert('Update Successful');
    }

    function logout() {
        userServiceClient.logout()
            .then(navigateToLogin);
    }

    function renderUser(user) {
        currentUser = user;
        $username.val(user.username);
        $password.val(user.password);
        $email.val(user.email);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
        $role.val(user.role);
        $phoneNumber.val(user.phone);
        $dateOfBirth.val(user.dateOfBirth);
    }

    function profile() {
        userServiceClient.profile()
            .then(function (response) {
                return response.json();
            });
    }

    function findUserById(userId) {
        return fetch('/api/user/' + userId)
            .then(function (response) {
                return response.json();
            });
    }

    function navigateToLogin() {
        window.location.href = '../login/login.template.client.html';
    }

})();