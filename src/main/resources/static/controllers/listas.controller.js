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
                //$scope.$applyAsync();
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
                    if (resp.data[0]) {
                        $scope.tareas[resp.data[0].lista.nombre] = resp.data;
                    }
                    //$scope.$applyAsync();
                    //$log.log($scope.tareas[key.nombre]);
                },
                function (err) {}
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
            function(err){
            }
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
            function(err){
            }
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

	$scope.moverLista=function(tarea, nombreLista){
        $log.log("Mover lista");
        for (var lista in $scope.listas) {
            if ($scope.listas[lista].nombre == nombreLista){        //toLowerCase?
                var body = JSON.stringify({"lista": $scope.listas[lista]});
                $log.log(body);
                tareasService.update(tarea.id, body).then(   //ID de la tarea que quiero mover, tarea con id de lista a la que quiero mover
                    function (resp) {
                        if (resp.status==200) {
                            //$scope.tareas[tarea.lista.nombre].splice(index, 1);
                            //$scope.tareas[nombreLista].push(resp.data);
                            //$scope.$applyAsync();
                            $scope.refresh();
                        }
                    },
                    function (err) {
                    }
                );
            }
        }
    };

    /*$scope.test=function(tarea,lista) {
        $log.log("Mover lista con drag and drop");
        $log.log(tarea);
        $log.log(lista);
        $scope.tareas[lista].push(tarea);
        for (var l in $scope.listas) {
            var key = $scope.listas[l];
            $log.log("Listas");
            $log.log(key.nombre);
            $log.log(key.id);
        }
        return true;
    };*/

	$scope.mostrarBotonGuardarLista=function(){
		var i=$scope.instanciaL;
		return i.nombre &&  i.nombre.length>0 && i.sprint && i.sprint.length>0;
	};

    $scope.mostrarBotonGuardarTarea=function(){
        var i=$scope.instanciaT;
        return i.nombre &&  i.nombre.length>0 && i.fechacreacion && i.fechacreacion.length>0;
    };

    $scope.mostrarListaVacia=function(lista){
        return $scope.tareas[lista].length <= 0;
    };

    $scope.changeDate=function(milliseconds){
        var raw = new Date(milliseconds);
        var day = raw.getDate();
        var month = raw.getMonth(); //Be careful! January is 0 not 1
        var year = raw.getFullYear();
        return day + "-" +(month + 1) + "-" + year;
    }
	
	$scope.refresh();
});