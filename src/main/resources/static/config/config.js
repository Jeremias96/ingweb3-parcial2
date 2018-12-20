angular.module('iw3')
.config(function($routeProvider,$locationProvider,$httpProvider,$logProvider){
    console.log('Configurando...');
    $logProvider.debugEnabled(true);


    $httpProvider.defaults.withCredentials = true;
    $httpProvider.interceptors.push('APIInterceptor');

    $locationProvider.hashPrefix('!');

	$routeProvider
	.when('/',{
		templateUrl : 'views/main.html',
		controller : 'MainController'
	})
	.when('/tablero',{
		templateUrl : 'views/tablero.html',
		controller : 'TableroController'
	})
	.otherwise({
		redirectTo: '/'
	});
});