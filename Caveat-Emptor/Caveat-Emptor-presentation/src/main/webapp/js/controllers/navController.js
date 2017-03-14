mainApp.controller('navController', NavigationController);

NavigationController.$inject = [ '$scope', '$rootScope' ];
function NavigationController($scope, $rootScope) {

	var ctrl = this;
	ctrl.displayLink = DisplayLink;

	ctrl.links = [ {
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

	ctrl.managementLinks = [ '#/users', '#/categories' ];

	function DisplayLink(link) {
		if (ctrl.managementLinks.includes(link.url))
			return $rootScope.globals.currentUser.isAdmin;
		return true;
	}

};