function LoginServiceClient () {

    this.login = login;


    function login (userObjectStr) {
        return fetch('/login', {
            method: 'post',
            body: userObjectStr,
            credentials: 'include',
            headers: {
                'content-type': 'application/json'
            }
        });


    }
}