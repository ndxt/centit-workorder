(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionAssignController', QuestionAssignController)

  /** @ngInject */
  function QuestionAssignController($stateParams,$uibModalInstance) {
    let vm = this;

    vm.cancel = $uibModalInstance.dismiss
    vm.ok = vm.sumbit

    function submit() {

    }

  }
})();

