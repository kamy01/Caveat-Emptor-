mainApp.config(config).run(run);

config.$inject = [ '$routeProvider' ];
function config($routeProvider) {
	$routeProvider.when('/login', {
		templateUrl : 'views/login.html',
		controller : 'loginController',
		controllerAs : 'ctrl'
	}).when('/register', {
		templateUrl : 'views/register.html',
		controller : 'registerController',
		controllerAs : 'ctrl'
	}).when('/', {
		templateUrl : 'views/home.html',
		controller : '',
		controllerAs : 'ctrl'
	}).when('/home', {
		templateUrl : 'views/home.html',
		controller : '',
		controllerAs : 'ctrl'
	}).otherwise({
		redirectTo : '/login'
	})
}

run.$inject = [ '$rootScope', '$location', '$cookies', '$http' ];
function run($rootScope, $location, $cookies, $http) {

	$rootScope.globals = $cookies.getObject('globals') || {};
	if ($rootScope.globals.currentUser) {
		$http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authData;
	}

	$rootScope.$on('$locationChangeStart', function(event, next, current) {
		var restrictedPage = $.inArray($location.path(), [ '/login', '/register' ]) === -1;
		var loggedIn = $rootScope.globals.currentUser;
		if (restrictedPage && !loggedIn) {
			$location.path('/login');
		}
	});
}