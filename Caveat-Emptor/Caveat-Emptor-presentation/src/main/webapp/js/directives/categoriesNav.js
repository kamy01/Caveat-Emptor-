mainApp.directive('catNav', CategoriesNav);

function CategoriesNav() {
	return {
		restrict : 'E',
		controller : 'catNavController',
		controllerAs : 'ctrl',
		templateUrl : 'views/templates/categoriesNav.html'
	}
}