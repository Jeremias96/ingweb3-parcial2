angular.module('iw3')
.factory('coreService',
		['$http','$q','URL_API_BASE','URL_BASE',
		function($http, $q, URL_API_BASE, URL_BASE) {
			return {
				logout: function() {
					return $http.get(URL_BASE+"logout");
				},
				authInfo: function(token) {
					 return $http.get(URL_BASE+"authinfo");
				},
				login: function(user) {
					var req = {
						method: 'POST',
						url: URL_BASE+'dologin',
						headers : { 'Content-Type': 'application/x-www-form-urlencoded' },
						data: 'remember-me=true&username='+user.name+'&password='+user.password
					};
					return $http(req);
				}
		} 
	}
]);