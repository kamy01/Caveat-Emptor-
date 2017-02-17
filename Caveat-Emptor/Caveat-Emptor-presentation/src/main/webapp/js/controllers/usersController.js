mainApp.controller('usersController', UsersController);

UsersController.$inject = [ 'userService', '$location', '$rootScope',
		'messagingService' ];
function UsersController(userService, $location, $rootScope, messagingService) {

	var ctrl = this;
	ctrl.itemsPerPage = 2;

	init();

	ctrl.setSortType = SetSortType;
	ctrl.getSortType = GetSortType;
	ctrl.isReverse = IsReverse;
	ctrl.updateUserStatus = UpdateUserStatus;

	function init() {
		ctrl.users = [];
		userService.getAll().then(function(response) {
			if (response.success) {
				ctrl.users = response.data;
			} else {
				messagingService.error(response.message)
			}
		});

		ctrl.sortSetup = {
			type : null,
			reverse : false
		};

	}

	function SetSortType(sortColumn, event) {
		if (event.target.type == 'text') {
			return;
		}
		if (ctrl.sortSetup.type !== sortColumn) {
			ctrl.sortSetup.reverse = false;
		} else {
			ctrl.sortSetup.reverse = !ctrl.sortSetup.reverse;
		}
		ctrl.sortSetup.type = sortColumn;
	}

	function UpdateUserStatus(user) {
		var userClone = angular.copy(user);
		userClone.enabled = !userClone.enabled;
		userService.update(userClone).then(function(response) {
			if (response.success) {
				angular.copy(userClone, user);
			} else {
				messagingService.error(response.message)
				return false;
			}
		});
	}

	function GetSortType() {
		return ctrl.sortSetup.type;
	}

	function IsReverse() {
		return ctrl.sortSetup.reverse;
	}

};