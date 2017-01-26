mainApp.service('messagingService', MessagingService);

MessagingService.$inject = ['$rootScope'];
function MessagingService($rootScope) {
    
    this.success = Success;
    this.error = Error;
    initService();
    
    function initService() {
        $rootScope.$on('$locationChangeStart', function(){
            clearMessage();
        });
        
        function clearMessage(){
            var message = $rootScope.message;
            if(message) {
                if(!message.keepAfterLocationChange) {
                    delete $rootScope.message;
                } else {
                    message.keepAfterLocationChange = false;
                }
            }
        }
    }
    
    function Success(message, keepAfterLocationChange) {
        $rootScope.message = {
            message: message,
            type: 'success',
            keepAfterLocationChange: keepAfterLocationChange
        }
    }
    
    function Error(message, keepAfterLocationChange) {
        $rootScope.message = {
            message: message,
            type: 'error',
            keepAfterLocationChange: keepAfterLocationChange
        }
    }
    
}