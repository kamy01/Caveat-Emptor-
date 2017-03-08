mainApp.service('itemService', ItemService);

ItemService.$inject = ['$http']
function ItemService($http){
    
    var COMPLETE_ITEMS_WS_URL = CONSTS.GENERAL_WS_URL + CONSTS.ITEMS_URL;
    
    this.getAll = GetAll;
    this.getAllWithCriteria = GetAllWithCriteria;
    this.getById = GetById;
    this.create = Create;
    this.update = Update;
    this.delete = Delete;
    
    function GetAll() {
        return $http.get(COMPLETE_ITEMS_WS_URL).then(handleSuccess, handleError('Error getting all items'))
    }

    function GetAllWithCriteria(criteria) {
    	return $http.get(COMPLETE_ITEMS_WS_URL + '/' + criteria.option + '/' + criteria.id).then(handleSuccess, handleError('Error getting all items'))
    }
    
    function GetById(id) {
        return $http.get(COMPLETE_ITEMS_WS_URL + '/' + id).then(handleSuccess, handleError('Error getting item by id'))
    }
    
    function Create(item) {
        return $http.post(COMPLETE_ITEMS_WS_URL, item).then(handleSuccess, handleError('Error creating item'))
    }
    
    function Update(item) {
        return $http.put(COMPLETE_ITEMS_WS_URL + '/' + item.id, item).then(handleSuccess, handleError('Error updating item'))
    }
    
    function Delete(id) {
        return $http.delete(COMPLETE_ITEMS_WS_URL + '/' + id).then(handleSuccess, handleError('Error deleting item'))
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