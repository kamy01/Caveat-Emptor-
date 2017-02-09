mainApp.controller('categoriesController', CategoriesController);

CategoriesController.$inject = [ '$scope' ];
function CategoriesController($scope) {
	$scope.categories = [ {
		name : "Processors",
		id : 1,
		children : [ {
			name : "AMD",
			id : 8,
			children : [ {
				name : "32Bit",
				id : 3,
				children : []
			}, {
				name : "64Bit",
				id : 4,
				children : []
			}, {
				name : "NoBit",
				id : 5,
				children : []
			} ]
		}, {
			name : "Intel",
			id : 7,
			children : []
		} ]
	}, {
		name : "Graphics Cards",
		id : 2,
		children : [ {
			name : "Nvidia",
			id : 6,
			children : [ {
				name : "Grandchild1",
				id : 9,
				children : []
			}, {
				name : "Grandchild2",
				id : 10,
				children : []
			}, {
				name : "Grandchild3",
				id : 11,
				children : []
			} ]
		}, {
			name : "Msi",
			id : 12,
			children : []
		} ]
	} ];
}