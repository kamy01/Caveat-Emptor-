mainApp.directive("tree", TreeDirective);

TreeDirective.$inject = [ '$compile' ];
function TreeDirective($compile) {
	return {
		restrict : 'E',
        requires: '^^catNav',
		scope : {
			items : '='
		},
		templateUrl : 'views/templates/category.html',
		compile : function(tElement, tAttr) {
			var contents = tElement.contents().remove();
			var compiledContents;
			return function(scope, iElement, iAttr) {
				if (!compiledContents) {
					compiledContents = $compile(contents);
				}
				compiledContents(scope, function(clone, scope) {
					iElement.append(clone);
				});
			};
		},
		link : function(scope, element, attr, categoriesController) {
			scope.parentId = attr.id;
		}
	}
}