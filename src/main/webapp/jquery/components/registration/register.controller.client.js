// IIFE
// Immediately Invoked Function Expression

(function () {

    var registerBtn = jQuery('#registerBtn');
    var usernameFld = $('#username');
    var passwordFld = $('#password');
    var password2Fld = $('#password2');
    var email = $('#email');

    var userServiceClient = new UserServiceClient();

    registerBtn.click(registerHandler);

    function registerHandler() {
        var usernameStr = usernameFld.val();
        var passwordStr = passwordFld.val();
        var password2Str = password2Fld.val();
        var emailStr = email.val();

        var userObj = {
            username: usernameStr,
            password: passwordStr,
            email: emailStr
        };

        var userObjStr = JSON.stringify(userObj);

        if (passwordFld != password2Fld) {
            alert("Passwords do not match");
        }

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
