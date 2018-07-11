function UserServiceClient () {

    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.createUser = createUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;

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



}