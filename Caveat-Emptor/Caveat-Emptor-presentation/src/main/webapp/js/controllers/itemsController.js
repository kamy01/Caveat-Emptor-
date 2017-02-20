mainApp.controller('itemsController', ItemsController);

ItemsController.$inject = [ '$scope', '$rootScope', 'itemService',
		'messagingService' ];
function ItemsController($scope, $rootScope, itemService, messagingService) {

	var ctrl = this;

	ctrl.itemsPerPage = 2;
	ctrl.itemOption = '';

	init();

	ctrl.updateItems = UpdateItems;

	// table headers setup
	ctrl.setSortType = SetSortType;
	ctrl.getSortType = GetSortType;
	ctrl.isReverse = IsReverse;

	function init() {
		ctrl.noItemsFound = false;
		ctrl.userId = $rootScope.globals.currentUser.userId;
		ctrl.sortSetup = {
			type : null,
			reverse : false
		};

	}

	function UpdateItems() {
		if (ctrl.itemOption != '') {
			itemService.getAllWithCriteria({
				option : ctrl.itemOption,
				id : ctrl.userId
			}).then(
					function(response) {
						if (response.success) {
							ctrl.noItemsFound = response.data == null
									|| response.data.length == 0;
							if (!ctrl.noItemsFound) {
								ctrl.items = modifyItemList(response.data);
							} else {
								ctrl.items = null;
							}
						} else {
							messagingService.error(response.message);
							ctrl.noItemsFound = true;

						}
					});
		} else {
			ctrl.noItemsFound = false;
			ctrl.items = null;
		}
	}

	function modifyItemList(items) {
		items.forEach(function(item) {
			if (item.categories != null && item.categories.length != 0) {
				item.categories = item.categories.map(function(category) {
					return category.name;
				}).join();
			} else {
				item.categories = 'No category';
			}
		});
		return items;
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

	function GetSortType() {
		return ctrl.sortSetup.type;
	}

	function IsReverse() {
		return ctrl.sortSetup.reverse;
	}

}