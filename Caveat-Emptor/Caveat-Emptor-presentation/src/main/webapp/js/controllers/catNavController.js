mainApp.controller('catNavController', CategoriesController);

CategoriesController.$inject = [ '$scope', '$rootScope', 'categoriesService', 'messagingService' ];
function CategoriesController($scope, $rootScope, categoriesService, messagingService) {

	init();

	function init() {
		categoriesService.getAll().then(function(response){
			if(response.success) {
				$scope.categories = response.data;
			} else {
				messagingService.error(response.message);
			}
		})
	}

}