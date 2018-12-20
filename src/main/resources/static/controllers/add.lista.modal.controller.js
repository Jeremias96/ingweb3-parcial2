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