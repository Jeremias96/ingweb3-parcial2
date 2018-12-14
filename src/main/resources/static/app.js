
var app=angular.module('iw3',['ngRoute','dndLists']);

app.constant('URL_BASE','/');
app.constant('URL_API_BASE','/api/v1/');

app.run(['$location','$log','$rootScope',
	function($location,$log,$rootScope){
		$log.log('Iniciando');
		$rootScope.relocate=function(loc) {
			$location.path(loc);
		};
	}
]);

