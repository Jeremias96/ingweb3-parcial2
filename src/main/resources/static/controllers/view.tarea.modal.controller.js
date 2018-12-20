angular.module('iw3')
    .controller('ViewTareaModalController', function($uibModalInstance,instancia){
        var $ctrl=this;
        $ctrl.prioridades=['Alta','Media','Baja'];
        $ctrl.instancia=angular.copy(instancia);
        $ctrl.ok=function(){
            $uibModalInstance.close();

        };
        $ctrl.changeDate=function(milliseconds){
            var raw = new Date(milliseconds);
            var day = raw.getDate();
            var month = raw.getMonth(); //Be careful! January is 0 not 1
            var year = raw.getFullYear();
            return day + "-" +(month + 1) + "-" + year;
        };

    });