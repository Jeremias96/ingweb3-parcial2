angular.module('iw3')
.factory('tareasService',function($http,URL_API_BASE){
	
	return {
		list : function(){
			return $http.get(URL_API_BASE+"tareas");
		},
        getByList : function(q){
            return $http.get(URL_API_BASE+"tareas?q=" + q);
        },
		add : function(t){
			return $http.post(URL_API_BASE+"tareas",t);
		}
		/*remove : function(id){
			return $http.delete(URL_API_BASE+"productos/"+id);
		}*/
	};
	
	
});