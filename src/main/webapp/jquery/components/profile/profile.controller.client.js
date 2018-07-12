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
            firstName: $firstName.val(),
            lastName: $lastName.val()
        };

        fetch("/api/profile", {
            method: 'put',
            body: JSON.stringify(user),
            'credentials': 'include',
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function updateSucceeded() {
        alert('Update Successful');
    }


    function logout() {
        fetch("/api/logout", {
            method: "post",
            'credentials': 'include',
            headers: {
                'content-type': 'application/json'
            }
        }).then(navigateToLogin);
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
        return fetch('/api/profile', {
            'credentials': 'include'
        })
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