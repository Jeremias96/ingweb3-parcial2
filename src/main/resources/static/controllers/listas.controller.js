angular.module('iw3')
.controller('ListasController', function($scope,$http,$log,$timeout,$uibModal,listasService,tareasService){
	$scope.titulo="Tablero";
	$scope.listas=[];	//Array
	$scope.tareas={};	//Diccionario
	$scope.instanciaL={};
    $scope.instanciaT={};
    $scope.sort;
	
	$scope.refresh=function() {
        listasService.list().then(
			function(resp){
				$scope.listas=resp.data;
                for (var lista in $scope.listas) {
                    var key = $scope.listas[lista];
                    $scope.tareas[key.nombre] = [];

                    tareasService.getByList(key.nombre).then(
                        function (resp) {
                            if (resp.data[0]) {
                                $scope.tareas[resp.data[0].lista.nombre] = resp.data;
                                /*$scope.tareas[resp.data[0].lista.nombre] = [];
                                for (var t in resp.data) {
                                    $scope.tareas[resp.data[0].lista.nombre].push(resp.data[t]);
                                }*/
                            }
                        },
                        function (err) {
                            $log.log(err);
                            swal({
                                title: "¡Oops!",
                                text: "Ocurrio un error",
                                type:"error",
                                timer: 1000,
                                allowEscapeKey: true,
                                showConfirmButton: false
                            });
                        }
                    );
                }
			},
			function(err){}
		);
	};
	
	$scope.agregarLista=function(){
        listasService.add($scope.instanciaL).then(
            function(resp){
                $scope.listas.push(resp.data);
                $scope.tareas[resp.data.nombre] = [];
                $scope.instanciaL={};
            },
            function(err){
                $scope.instanciaL={};
                $log.log(err);
                swal({
                    title: "¡Oops!",
                    text: "Operacion no permitida",
                    type:"info",
                    timer: 1000,
                    allowEscapeKey: true,
                    showConfirmButton: false
                });
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
                $scope.instanciaT={};
                $log.log(err);
                swal({
                    title: "¡Oops!",
                    text: "Ocurrio un error",
                    type:"error",
                    timer: 1000,
                    allowEscapeKey: true,
                    showConfirmButton: false
                });
            }
        );
    };

	$scope.borrar=function(id){
		/*if(!confirm("Desea eliminar la tarea seleccionada?"))
			return;*/
        tareasService.remove(id).then(
            function(resp){
                for (var key in $scope.tareas){
                    for (var index in $scope.tareas[key]){
                        if ($scope.tareas[key][index].id == id){
                            $scope.tareas[key].splice(index, 1);
                            break;
                        }
                    }
                }
            },
            function(err){
                $log.log(err);
                swal({
                    title: "¡Oops!",
                    text: "Ocurrio un error",
                    type:"error",
                    timer: 1000,
                    allowEscapeKey: true,
                    showConfirmButton: false
                });
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
                    $log.log(err);
                    swal({
                        title: "¡Oops!",
                        text: "Ocurrio un error",
                        type:"error",
                        timer: 1000,
                        allowEscapeKey: true,
                        showConfirmButton: false
                    });
                }
            );
        }
    };

	$scope.moverLista=function(tarea, nombreLista){
	    if (tarea.lista.nombre != nombreLista){
            for (var lista in $scope.listas) {
                if ($scope.listas[lista].nombre == nombreLista){
                    var body = JSON.stringify({"lista": $scope.listas[lista]});
                    tareasService.update(tarea.id, body).then(   //ID de la tarea que quiero mover, tarea con id de lista a la que quiero mover
                        function (resp) {
                            for (t in $scope.tareas[tarea.lista.nombre]){
                                if ($scope.tareas[tarea.lista.nombre][t].id == tarea.id ){
                                    $scope.tareas[tarea.lista.nombre].splice(t, 1);
                                    $scope.tareas[nombreLista].push(resp.data);
                                    break;
                                }
                            }
                            //$scope.refresh();
                        },
                        function (err) {
                            $log.log(err);
                            swal({
                                title: "¡Oops!",
                                text: "Operacion no permitida",
                                type:"info",
                                timer: 1000,
                                allowEscapeKey: true,
                                showConfirmButton: false
                            });
                        }
                    );
                }
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
            keyboard : true,
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
            keyboard : true,
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
            keyboard : true,
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

    // Watch sobre tareas, se ejecuta cuando cambia el valor de tareas.
    /*$scope.$watch("tareas",function(newValue,oldValue) {
        $log.log("Watched");
        $log.log(oldValue);
        $log.log(newValue);
    }, true);*/

    // On event content loaded
    /*$scope.$on('$viewContentLoaded', function(){
        $log.log('Loaded');
        $scope.$apply(function () {
            $scope.refresh();
        });
    });*/

    // time out, se ejecuta cuando terminaron todos los digest
    // los apply ejecutan un digest
    // un digest ejecuta todos los watch
    // nunca se ejecutan mas de un digest al mismo tiempo
    // al pulsar un boton, agular envuelve la funcion en un apply, y sucede lo anterior
    /*$timeout(function(){
        //$log.log('Timeout');
        $scope.$apply(function () {
            $scope.refresh();
        });
    });*/

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
        $ctrl.ok=function(){
            $uibModalInstance.close(/*$ctrl.instancia*/);

        };
        $ctrl.changeDate=function(milliseconds){
            var raw = new Date(milliseconds);
            var day = raw.getDate();
            var month = raw.getMonth(); //Be careful! January is 0 not 1
            var year = raw.getFullYear();
            return day + "-" +(month + 1) + "-" + year;
        };
    });
