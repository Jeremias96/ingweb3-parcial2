angular.module('iw3')
.controller('ListasController', function($scope,$http,$log,$uibModal,listasService,tareasService){
	$scope.titulo="Listas";
	$scope.listas=[];	//Array
	$scope.tareas={};	//Diccionario
	$scope.instanciaL={};
    $scope.instanciaT={};
    $scope.sort;
	
	$scope.refresh=function() {
        listasService.list().then(
			function(resp){
				$scope.listas=resp.data;
			},
			function(err){}
		);
        for (var lista in $scope.listas) {
            var key = $scope.listas[lista];
            $scope.tareas[key.nombre] = [];

            tareasService.getByList(key.nombre).then(
                function (resp) {
                    if (resp.data[0]) {
                        $scope.tareas[resp.data[0].lista.nombre] = resp.data;
                    }
                },
                function (err) {}
            );
        }
	};
	
	$scope.agregarLista=function(){
        listasService.add($scope.instanciaL).then(
            function(resp){
                $scope.listas.push(resp.data);
                $scope.tareas[resp.data.nombre] = [];
                $scope.instanciaL={};
            },
            function(err){
            }
        );
	};

    $scope.agregarTarea=function(){
        tareasService.add($scope.instanciaT).then(
            function(resp){
                $scope.tareas[resp.data.lista.nombre].push(resp.data);
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
                for (var key in $scope.tareas){
                    for (var index in $scope.tareas[key]){
                        if ($scope.tareas[key][index].id == id){
                            $scope.tareas[key].splice(index, 1);
                            break;
                        }
                        index++;
                    }
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
            if ($scope.listas[lista].nombre == nombreLista){
                var body = JSON.stringify({"lista": $scope.listas[lista]});
                $log.log(body);
                tareasService.update(tarea.id, body).then(   //ID de la tarea que quiero mover, tarea con id de lista a la que quiero mover
                    function (resp) {
                        var index = 0;
                        for (t in $scope.tareas[tarea.lista.nombre]){
                            if (t == tarea ){
                                break;
                            }
                            index++;
                        }
                        $scope.tareas[tarea.lista.nombre].splice(index, 1);
                        $scope.tareas[nombreLista].push(resp.data);
                        $scope.refresh();
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

	/*$scope.mostrarBotonGuardarLista=function(){
		var i=$scope.instanciaL;
		return i.nombre &&  i.nombre.length>0 && i.sprint && i.sprint.length>0;
	};*/

    /*$scope.mostrarBotonGuardarTarea=function(){
        var i=$scope.instanciaT;
        return i.nombre &&  i.nombre.length > 0 && i.prioridad && i.prioridad.length > 0 && i.estimacion && i.estimacion > 0;
    };*/

    $scope.mostrarListaVacia=function(lista){
        return $scope.tareas[lista].length <= 0;
    };

    $scope.changeDate=function(milliseconds){
        var raw = new Date(milliseconds);
        var day = raw.getDate();
        var month = raw.getMonth(); //Be careful! January is 0 not 1
        var year = raw.getFullYear();
        return day + "-" +(month + 1) + "-" + year;
    };

    $scope.nuevoModalLista=function() {
        var mi=$uibModal.open({
            animation : true,
            backdrop : 'static',
            keyboard : false,
            templateUrl : 'views/addListaModal.html',
            controller : 'AddListaModalController',
            controllerAs: '$ctrl',
            size : 'large',
            resolve : {
                instancia : $scope.instanciaL
            }
        });

        mi.result.then(
            function(r){
                $log.log($scope.instanciaL);
                if (r != null) {
                    $scope.instanciaL=r;
                    $scope.agregarLista();
                }
            },
            function(e){
            });
    };

    $scope.nuevoModalTarea=function() {
        var mi=$uibModal.open({
            animation : true,
            backdrop : 'static',
            keyboard : false,
            templateUrl : 'views/addTareaModal.html',
            controller : 'AddTareaModalController',
            controllerAs: '$ctrl',
            size : 'large',
            resolve : {
                instancia : $scope.instanciaT
            }
        });

        mi.result.then(
            function(r){
                if (r != null) {
                    $scope.instanciaT=r;
                    $scope.agregarTarea();
                }
            },
            function(e){
            });
    };

    $scope.nuevoModalViewTarea=function(tarea) {
        var mi=$uibModal.open({
            animation : true,
            backdrop : 'static',
            keyboard : false,
            templateUrl : 'views/viewTareaModal.html',
            controller : 'ViewTareaModalController',
            controllerAs: '$ctrl',
            size : 'large',
            resolve : {
                instancia : tarea,
            }
        });

        mi.result.then(
            function(r){
                if (r != null) {
                    $log.log(r);
                    //$scope.instanciaT=r;
                    //$scope.actualizarTarea(); //servicio no desarrollado
                }
            },
            function(e){
            });
    };

    //Listo para agregar servicio actualizar cuando este listo
    /*$scope.$watch("tareas",function(newValue,oldValue) {
        $log.log("Watched!");
        $log.log(oldValue);
        $log.log(newValue);
    }, true);*/

	$scope.refresh();
});

angular.module('iw3')
.controller('AddListaModalController', function($uibModalInstance,instancia){
    var $ctrl=this;
    $ctrl.instancia=angular.copy(instancia);
    $ctrl.cancelar=function(){
        $uibModalInstance.close();
    };
    $ctrl.ok=function(){
        $uibModalInstance.close($ctrl.instancia);

    };
    $ctrl.mostrarBotonGuardar=function(){
        var i = $ctrl.instancia;
        return i.nombre &&  i.nombre.length > 0 && i.sprint && i.sprint.length > 0;
    };
});

angular.module('iw3')
    .controller('AddTareaModalController', function($uibModalInstance,instancia){
        var $ctrl=this;
        $ctrl.instancia=angular.copy(instancia);
        $ctrl.cancelar=function(){
            $uibModalInstance.close();
        };
        $ctrl.ok=function(){
            $uibModalInstance.close($ctrl.instancia);

        };
        $ctrl.mostrarBotonGuardar=function(){
            var i = $ctrl.instancia;
            return i.nombre &&  i.nombre.length > 0 && i.prioridad && i.prioridad.length > 0 && i.estimacion && i.estimacion > 0;
        };
    });

angular.module('iw3')
    .controller('ViewTareaModalController', function($uibModalInstance,instancia){
        var $ctrl=this;
        $ctrl.instancia=angular.copy(instancia);
        /*$ctrl.cancelar=function(){
            $uibModalInstance.close();
        };*/
        $ctrl.ok=function(){
            $uibModalInstance.close(/*$ctrl.instancia*/);

        };
        $ctrl.mostrarBotonGuardar=function(){
            var i = $ctrl.instancia;
            //return i.nombre &&  i.nombre.length > 0 && i.prioridad && i.prioridad.length > 0 && i.estimacion && i.estimacion > 0;
            return true;
        };
        $ctrl.changeDate=function(milliseconds){
            var raw = new Date(milliseconds);
            return raw;//.toDateString();
        };
    });
