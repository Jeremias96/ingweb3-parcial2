angular.module('iw3')
.factory('listasService',function($http,URL_API_BASE){
	
	return {
		list : function(){
			return $http.get(URL_API_BASE+"listas");
		},
		add : function(l){
			return $http.post(URL_API_BASE+"listas",l);
		}
	};
});