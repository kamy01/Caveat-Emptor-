mainApp.service('userService', UserService);

UserService.$inject = ['$http']
function UserService($http){
    
    var COMPLETE_USERS_WS_URL = CONSTS.GENERAL_WS_URL + CONSTS.USERS_URL;
    
    this.getAll = GetAll;
    this.getById = GetById;
    this.create = Create;
    this.update = Update;
    this.delete = Delete;
    
    function GetAll() {
        return $http.get(COMPLETE_USERS_WS_URL).then(handleSuccess, handleError('Error getting all users'))
    }
    
    function GetById(id) {
        return $http.get(COMPLETE_USERS_WS_URL + '/' + id).then(handleSuccess, handleError('Error getting user by id'))
    }
    
    function Create(user) {
        return $http.post(COMPLETE_USERS_WS_URL + '/register', user).then(handleSuccess, handleError('Error creating user'))
    }
    
    function Update(user) {
        return $http.put(COMPLETE_USERS_WS_URL + '/' + user.id, user).then(handleSuccess, handleError('Error updating user'))
    }
    
    function Delete(id) {
        return $http.delete(COMPLETE_USERS_WS_URL + '/' + id).then(handleSuccess, handleError('Error deleting user'))
    }
    
    function handleSuccess(response) {
        return response.data;
    }
    
    function handleError(errorMessage) {
        return function() {
            return {
                success: false,
                message: errorMessage
            }
        }
    }
    
}