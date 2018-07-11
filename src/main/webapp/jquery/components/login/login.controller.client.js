(function () {
    var $username,
        $password,
        $loginBtn;
        var loginServiceClient = new LoginServiceClient();

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
        loginServiceClient.login(userObjectStr)
            .then(navigateToProfile);
    }

    function navigateToProfile() {
        window.location.href = '/components/profile/profile.template.client.html';
    }
})();