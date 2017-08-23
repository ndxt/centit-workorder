(function(){
  'user strict'

  angular.module('workorder')
    .controller('ConfirmModalController', ConfirmModalController)

  function ConfirmModalController($uibModalInstance, title) {
    const vm = this;
    vm.title = title;

    vm.cancel = cancel;
    vm.ok = ok;

    function cancel(){
      $uibModalInstance.dismiss();

    }

    function ok() {
      $uibModalInstance.close(true);
    }
  }

})();
