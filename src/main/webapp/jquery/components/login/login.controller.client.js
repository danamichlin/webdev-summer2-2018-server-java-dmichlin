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