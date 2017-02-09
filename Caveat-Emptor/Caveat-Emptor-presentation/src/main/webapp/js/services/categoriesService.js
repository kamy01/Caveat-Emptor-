mainApp.service('categoriesService', CategoriesService);

CategoriesService.$inject = ['$http']
function CategoriesService($http){
    
    var COMPLETE_CATEGORIES_WS_URL = CONSTS.GENERAL_WS_URL + CONSTS.CATEGORIES_URL;
    
    this.getAll = GetAll;
    this.getById = GetById;
    this.create = Create;
    this.update = Update;
    this.delete = Delete;
    
    function GetAll() {
        return $http.get(COMPLETE_CATEGORIES_WS_URL).then(handleSuccess, handleError('Error getting all categories'))
    }
    
    function GetById(id) {
        return $http.get(COMPLETE_CATEGORIES_WS_URL + '/' + id).then(handleSuccess, handleError('Error getting category by id'))
    }
    
    function Create(category) {
        return $http.post(COMPLETE_CATEGORIES_WS_URL, category).then(handleSuccess, handleError('Error creating category'))
    }
    
    function Update(category) {
        return $http.put(COMPLETE_CATEGORIES_WS_URL + '/' + category.id, category).then(handleSuccess, handleError('Error updating category'))
    }
    
    function Delete(id) {
        return $http.delete(COMPLETE_CATEGORIES_WS_URL + '/' + id).then(handleSuccess, handleError('Error deleting category'))
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