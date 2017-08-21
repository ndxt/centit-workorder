(function () {
  'user strict'

  angular.module('workorder')
    .controller('ConfirmModalController', ConfirmModalController)

  function ConfirmModalController($uibModalInstance, title) {
    const vm = this;
    vm.title = title;

    vm.cancle = cancle;
    vm.ok = ok;

    function cancle() {
      $uibModalInstance.dismiss();

    }

    function ok() {
      $uibModalInstance.close(true);
    }
  }

})();
