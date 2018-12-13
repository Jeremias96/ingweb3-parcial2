angular.module('iw3')
.controller('ListasController', function($scope,$http,$log,listasService,tareasService){
	$scope.titulo="Listas";
	$scope.listas=[];	//Array
	$scope.tareas={};	//Diccionario
	$scope.instanciaL={};
    $scope.instanciaT={};
	$scope.instanciaM=false;
	
	//{"id":1,"descripcion":"Leche","precio":3.0,"enStock":true,"vencimiento":"1900-01-01T04:16:48.000+0000","rubro":{"idRubro":1,"descripcion":"Alimentos"}}]
	
	$scope.refresh=function() {
        listasService.list().then(
			function(resp){
				$scope.listas=resp.data;
			},
			function(err){}
		);
        /*tareasService.list().then(
        	function(resp){
        		$scope.tareas=resp.data;
			},
			function(err){}
		);*/
        //Recorrer tareas de cada lista (recorrer lista)
        for (var lista in $scope.listas) {
            var key = $scope.listas[lista];
            //$log.log("Lista: " + key.nombre);
            $scope.tareas[key.nombre] = [];

            tareasService.getByList(key.nombre).then(
                function (resp) {
                    //var clave = key.nombre;
                    if (resp.data[0])
                        $scope.tareas[resp.data[0].lista.nombre] = resp.data;
                    //$log.log($scope.tareas[key.nombre]);
                },
                function (err) {
                }
            );
        }
	};
	
	$scope.agregarLista=function(){
        listasService.add($scope.instanciaL).then(
				function(resp){
					$scope.listas.push(resp.data);
                    $scope.refresh();
					$scope.instanciaL={};
				}, 
				function(err){} 
			);
	};

    $scope.agregarTarea=function(){
        tareasService.add($scope.instanciaT).then(
            function(resp){
                $scope.tareas[resp.data.lista.nombre] = resp.data;
                //$scope.refresh();
                $scope.instanciaT={};
            },
            function(err){}
        );
    };
	$scope.borrar=function(id){
		if(!confirm("Desea eliminar la tarea seleccionada?"))
			return;
        tareasService.remove(id).then(
            function(resp){
                if(resp.status==200) {
                    $scope.refresh();
                    /*$scope.tareas.forEach(function(o,i){
                        if(o.id==id) {
                            $scope.tareas.splice(i,1);
                        }
                        });*/
                }
            },
            function(err){}
        );
	};

	$scope.sortBy=function(){
        for (var lista in $scope.listas) {
            var key = $scope.listas[lista];
            $scope.tareas[key.nombre] = [];

            tareasService.getByListSorted(key.nombre, $scope.sort).then(
                function (resp) {
                    if (resp.data[0])
                        $scope.tareas[resp.data[0].lista.nombre] = resp.data;
                },
                function (err) {
                }
            );
        }
    };

	$scope.moverLista=function(id, t){
        $log.log("Mover lista");
        $log.log(id);
        $log.log(t);
        for (var lista in $scope.listas) {
            if ($scope.listas[lista].nombre.toLowerCase() == t){
                var t2 = JSON.stringify({"lista": $scope.listas[lista]});
                $log.log(t2);
                tareasService.update(id, t2).then(   //ID de la tarea que quiero mover, tarea con id de lista a la que quiero mover
                    function (resp) {

                    },
                    function (err) {
                    }
                );
            }
        }
    };

	$scope.mostrarBotonGuardarLista=function(){
		var i=$scope.instanciaL;
		return i.nombre &&  i.nombre.length>0 && i.sprint && i.sprint.length>0;
	};

    $scope.mostrarBotonGuardarTarea=function(){
        var i=$scope.instanciaT;
        return i.nombre &&  i.nombre.length>0 && i.fechacreacion && i.fechacreacion.length>0;
    };
	
	
	$scope.refresh();
});