mainApp.service('authenticationService', authenticationService);

authenticationService.$inject = [ '$http', '$cookies', '$rootScope' ];
function authenticationService($http, $cookies, $rootScope) {

	this.login = Login;
	this.setCredentials = SetCredentials;
	this.clearCredentials = ClearCredentials;

	function Login(username, password, callback) {

		var authenticateUrl = CONSTS.GENERAL_WS_URL + CONSTS.AUTHENTICATE_URL;

		$http.post(authenticateUrl, {
			username : username,
			password : password
		}).success(function(response) {
			callback(response);
		});

	}

	function SetCredentials(username, password, userResponse) {
		var authData = Base64.encode(username + ':' + password);

		$rootScope.globals = {
			currentUser : {
				username : username,
				userId: userResponse.id,
				authData : authData,
				isAdmin : userResponse.admin
			},
			userLoggedIn : true
		};

		// set default auth header for http requests
		$http.defaults.headers.common['Authorization'] = 'Basic ' + authData;

		var cookieExp = new Date();
		cookieExp.setDate(cookieExp.getDate() + 7);
		$cookies.putObject('globals', $rootScope.globals, {
			expires : cookieExp
		});
	}

	function ClearCredentials() {
		$rootScope.globals = {};
		$cookies.remove('globals');
		$http.defaults.headers.common.Authorization = 'Basic';
	}

}