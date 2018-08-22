(function () {
    var $username,
        $password,
        $loginBtn;
        var userServiceClient = new UserServiceClient();

    function init() {
        $username = $('#username');
        $password = $('#password');
        $loginBtn = $('#loginBtn');

        $loginBtn.click(login);


    }
    init();

    function login() {

        if ($username.val() === "" && $password.val() === "") {
            alert("Please make sure username and password fields are filled out");
        }

        else if ($username.val() === "") {
            alert("Please enter correct username");
        }

        else if ($password.val() === "") {
            alert("Please enter correct password");
        }

        var user = {
            'username': $username.val(),
            "password": $password.val()
        };

        var userObjectStr = JSON.stringify(user);

        userServiceClient.login(userObjectStr)
            .then(function (response) {
                if (response != null) {
                    navigateToProfile();
                }
            });
    }

    function navigateToProfile() {
        window.location.href = '../profile/profile.template.client.html';
    }
})();