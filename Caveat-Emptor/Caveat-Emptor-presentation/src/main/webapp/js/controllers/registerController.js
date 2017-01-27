mainApp.controller('registerController', RegisterController);

RegisterController.$inject = [ 'userService', '$location', '$rootScope', 'messagingService' ];
function RegisterController(userService, $location, $rootScope, messagingService) {
	
	var ctrl = this;

	ctrl.register = Register;

	function Register() {
		userService.create(ctrl.user).then(function(response) {
			if (response.success) {
				messagingService.success('Registration successful', true);
				$location.path('/login');
			} else {
				messagingService.error(response.message);
			}
		});
	}
};