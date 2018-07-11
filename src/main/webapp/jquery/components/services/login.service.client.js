function LoginServiceClient () {

    this.login = login;


    function login (userObjectStr) {
        return fetch('/login', {
            method: 'Post',
            body: userObjectStr,
            credentials: 'include',
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            if (response.status == 403) {
                alert ("Incorrect Login");
                return null;
            }
            return response.json();
        });
    }
}