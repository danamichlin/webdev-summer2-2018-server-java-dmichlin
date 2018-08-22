function UserServiceClient () {

    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.createUser = createUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.login = login;
    this.registerHandler = registerHandler;
    this.updateUserProfile = updateUserProfile;

    var serverUrl = 'https://intense-journey-34677.herokuapp.com';

    function deleteUser(id) {
        var url = serverUrl + "/api/user/" + id;
        return fetch(url, {
            method: 'delete'
        })
    }

    function findAllUsers() {
        var url = serverUrl + "/api/user";
        return fetch(url)
            .then(function (response) {
                return response.json();
            });
    }

    function createUser(userObjStr) {
        return fetch('serverUrl + /api/user', {
            method: 'Post',
            body: userObjStr,
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    function findUserById(id) {
        var url = serverUrl + "/api/user/" + id;

        return fetch(url)
            .then(function (response) {
                return response.json();
            });
    }

    function updateUser(id, userObjStr) {
        var url = serverUrl + "/api/user/" + id;

        return fetch(url, {
            method: 'Put',
            body: userObjStr,
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    function login (userObjectStr) {
        return fetch(serverUrl + '/login', {
            method: 'Post',
            body: userObjectStr,
            credentials: 'include',
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            if (response.status == 403) {
                alert ("Login Unsuccessful");
                return null;
            }
            return response.json();
        });
    }

    function registerHandler (userObjStr) {
        fetch(serverUrl + '/register', {
            method: 'post',
            body: userObjStr,
            headers: {
                'Content-Type': 'application/json'
            },
            'credentials': 'include'
        }).then(function (response) {
            if (response.status === 409) {
                registrationFailed();
            }
            else
            {
                registrationSuccessful();
            }
        });;
    }

    function registrationSuccessful() {
        window.location.href = '../profile/profile.template.client.html';
    }

    function registrationFailed() {
        alert('Cannot register with this username: username already exists')
    }

    function updateUserProfile(user) {
        fetch(serverUrl + "/api/profile", {
            method: 'put',
            body: JSON.stringify(user),
            'credentials': 'include',
            headers: {
                'content-type': 'application/json'
            }
        })
    }



}