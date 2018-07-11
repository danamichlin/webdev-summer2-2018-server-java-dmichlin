(function () {

    var $username, $firstName, $lastName,
        $updateBtn, $logoutBtn;

    var currentUser = null;

    function init() {

        $username = $("#username");
        $firstName = $("#firstName");
        $lastName = $("#lastName");
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
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
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

    function handleResponse() {

    }
})();