function UserServiceClient () {

    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.createUser = createUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.login = login;
    this.registerHandler = registerHandler;
    this.updateUserProfile = updateUserProfile;
    this.logout = logout;
    this.profile = profile;

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
                alert ("Incorrect Login");
                return null;
            }
            return response.json();
        });
    }

    function registerHandler () {
        fetch('/register', {
            method: 'post',
            body: userObjStr,
            headers: {
                'Content-Type': 'application/json'
            },
            'credentials': 'include'
        });
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

    function logout() {
        fetch("/api/logout", {
            method: "post",
            'credentials': 'include',
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function profile() {
        return fetch('/api/profile', {
            'credentials': 'include'
        });
    }
}