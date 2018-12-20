angular.module('iw3')
.service('APIInterceptor', function($rootScope) {
    var service = this;

    service.responseError = function(response) {
       if(response.status==401) {
    	   $rootScope.openLoginForm();
       } else if(response.status==404){
           swal({
               title: "¡Oops!",
               text: "No encontrado",
               type:"info",
               timer: 1000,
               allowEscapeKey: true,
               showConfirmButton: false
           });
           $rootScope.authInfo();
       } else if(response.status==406){
           swal({
               title: "¡Oops!",
               text: "Operacion no permitida",
               type:"info",
               timer: 1000,
               allowEscapeKey: true,
               showConfirmButton: false
           });
           $rootScope.authInfo();
       } else if(response.status==500){
           swal({
               title: "¡Esto no debio pasar!",
               text: "Error en el servidor",
               type:"error",
               timer: 1000,
               allowEscapeKey: true,
               showConfirmButton: false
           });
           $rootScope.authInfo();
       } else {
    	   $rootScope.authInfo();
       }
       return response;
    };
});