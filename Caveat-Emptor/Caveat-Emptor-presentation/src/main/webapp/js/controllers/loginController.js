mainApp.controller('loginController', LoginController);

LoginController.$inject = [ '$location', 'authenticationService', 'messagingService' ];
function LoginController($location, authenticationService, messagingService) {

	var ctrl = this;
	ctrl.login = Login;
	initController();

	function initController() {
		authenticationService.clearCredentials();
	}

	function Login() {
		authenticationService.login(ctrl.user.username, ctrl.user.password, function(response) {
			if (response.success) {
				authenticationService.setCredentials(ctrl.user.username, ctrl.user.password, response.data);
				$location.path('/');
			} else {
				messagingService.error(response.message);
			}
		});
	}

};