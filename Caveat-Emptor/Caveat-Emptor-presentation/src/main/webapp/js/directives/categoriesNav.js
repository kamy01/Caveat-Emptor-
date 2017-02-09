mainApp.directive('catNav', CategoriesNav);

function CategoriesNav() {
    return {
        restrict: 'E',
        templateUrl: 'views/templates/categoriesNav.html'
    }
}