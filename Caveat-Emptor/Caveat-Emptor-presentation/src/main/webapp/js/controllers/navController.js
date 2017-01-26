mainApp.controller('navController', NavigationController);

NavigationController.$inject = [ '$scope', '$rootScope' ];
function NavigationController($scope, $rootScope) {
	$scope.links = [ {
		title : 'Home',
		url : "#/"
	} ];
};