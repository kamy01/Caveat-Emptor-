mainApp.controller('navController', NavigationController);

NavigationController.$inject = [ '$scope', '$rootScope' ];
function NavigationController($scope, $rootScope) {
	$scope.links = [ {
		title : 'Home',
		url : "#/"
	}, {
		title : 'Bidding',
		url : "#/bidding"
	}, {
		title : 'Items',
		url : "#/items"
	}, {
		title : 'Categories',
		url : "#/categories"
	}, {
		title : 'Users',
		url : "#/users"
	}, {
		title : 'Account',
		url : "#/account"
	} ];
};