mainApp.directive('catNav', CategoriesNav);

function CategoriesNav() {
	return {
		restrict : 'E',
		controller : 'categoriesController',
		controllerAs : 'ctrl',
		templateUrl : 'views/templates/categoriesNav.html'
	}
}