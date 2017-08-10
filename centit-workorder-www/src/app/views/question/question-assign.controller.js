(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionAssignController', QuestionAssignController)

  /** @ngInject */
  function QuestionAssignController($stateParams,$uibModalInstance,QuestionsAPI) {
    let vm = this;

    vm.cancel = $uibModalInstance.dismiss
    vm.ok = vm.sumbit

    function submit() {
      QuestionsAPI.updateOperator($stateParams,{currentOperator:vm.currentOperator})

    }

  }
})();

