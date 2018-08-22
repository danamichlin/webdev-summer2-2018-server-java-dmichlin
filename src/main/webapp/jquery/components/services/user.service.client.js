function UserServiceClient () {

    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.createUser = createUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.login = login;
    this.registerHandler = registerHandler;
    this.updateUserProfile = updateUserProfile;

    function deleteUser(id) {
        var url = "/api/user/" + id;
        return fetch(url, {
            method: 'delete'
        })
    }

    function findAllUsers() {
        var url = "/api/user";
        return fetch(url)
            .then(function (response) {
                return response.json();
            });
    }

    function createUser(userObjStr) {
        return fetch('/api/user', {
            method: 'Post',
            body: userObjStr,
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    function findUserById(id) {
        var url = "/api/user/" + id;

        return fetch(url)
            .then(function (response) {
                return response.json();
            });
    }

    function updateUser(id, userObjStr) {
        var url = "/api/user/" + id;

        return fetch(url, {
            method: 'Put',
            body: userObjStr,
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

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
                alert ("Login Unsuccessful");
                return null;
            }
            return response.json();
        });
    }

    function registerHandler (userObjStr) {
        fetch('/register', {
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
        fetch("/api/profile", {
            method: 'put',
            body: JSON.stringify(user),
            'credentials': 'include',
            headers: {
                'content-type': 'application/json'
            }
        })
    }



}