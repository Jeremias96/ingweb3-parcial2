
var app=angular.module('iw3',['ngRoute','dndLists','ui.bootstrap','mwl.confirm']);

app.constant('URL_BASE','/');
app.constant('URL_API_BASE','/api/v1/');

app.run(['$location','$log','$rootScope','$uibModal',
	function($location,$log,$rootScope,$uibModal){
		$log.log('Iniciando');
		$rootScope.relocate=function(loc) {
			$location.path(loc);
		};
	}
]);

