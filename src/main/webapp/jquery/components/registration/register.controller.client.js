// IIFE
// Immediately Invoked Function Expression

(function () {

    var registerBtn = jQuery('#registerBtn');
    var usernameFld = $('#username');
    var passwordFld = $('#password');
    var password2Fld = $('#password2');

    var userServiceClient = new UserServiceClient();

    registerBtn.click(registerHandler);

    function registerHandler() {
        var usernameStr = usernameFld.val();
        var passwordStr = passwordFld.val();
        var password2Str = password2Fld.val();

        var userObj = {
            username: usernameStr,
            password: passwordStr
        };

        var userObjStr = JSON.stringify(userObj);

        userServiceClient.registerHandler(userObjStr)
            .then(function (response) {
            if (response.status === 409) {
                registrationFailed();
            }
            else
            {
                registrationSuccessful();
            }
        });
    }


})();
