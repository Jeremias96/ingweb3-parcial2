angular.module('iw3')
.config(function($routeProvider,$locationProvider,$httpProvider){
	
	$routeProvider
	.when('/',{
		templateUrl : 'views/main.html',
		controller : 'MainController'
	})
	.when('/listas',{
		templateUrl : 'views/listas.html',
		controller : 'ListasController'
	})
	.otherwise({
		redirectTo: '/'
	});
});