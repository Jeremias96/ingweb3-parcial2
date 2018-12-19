angular.module('iw3').controller('MainController',
	function($scope, $rootScope){
	
		$scope.titulo="Menú";
        $rootScope.authInfo();
	
	}	
);