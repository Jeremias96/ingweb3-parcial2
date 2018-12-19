angular.module('iw3')
.service('APIInterceptor', function($rootScope) {
    var service = this;

    service.responseError = function(response) {
       if(response.status==401) {
    	   $rootScope.openLoginForm();
       } else {
    	   $rootScope.authInfo();
       }
       return response;
    };
})