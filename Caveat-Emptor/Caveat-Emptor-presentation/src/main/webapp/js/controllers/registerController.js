mainApp.controller('registerController', RegisterController);

RegisterController.$inject = [];
function RegisterController() {
	var ctrl = this;

	ctrl.register = function() {
		console.log('Registration successful');
	}
};