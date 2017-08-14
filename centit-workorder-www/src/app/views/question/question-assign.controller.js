(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionAssignController', QuestionAssignController)

  /** @ngInject */
  function QuestionAssignController($stateParams,$uibModalInstance,QuestionAPI) {
    let vm = this;

    vm.cancel = $uibModalInstance.dismiss
    vm.ok = submit
    vm.isSelected = isSelected

    vm.users = [{
      usercode:'u001',
      username:'张三'
    },{
      usercode:'u002',
      username:'李四'
    },{
      usercode:'u003',
      username:'王子俊'
    }]

    function submit() {
      QuestionAPI.updateOperator($stateParams,{currentOperator:vm.currentOperator})

    }
    function isSelected(user) {
      user.isChecked = !user.isChecked;

    }

  }
})();

