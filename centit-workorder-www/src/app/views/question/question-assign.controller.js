(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionAssignController', QuestionAssignController)

  /** @ngInject */
  function QuestionAssignController($stateParams,$uibModalInstance,QuestionAPI,questionId) {
    let vm = this;

    vm.cancel = $uibModalInstance.dismiss
    vm.ok = submit
    vm.isSelected = isSelected
    vm.questionId = questionId

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
      Promise.all([new Promise(function(resolve,reject){
          //主负责人数据
          QuestionAPI.updateOperator(Object.assign({questionId:vm.questionId},$stateParams),{questionId:vm.questionId,currentOperator:vm.currentOperator})
            .$promise
            .then(function(res){
              resolve(res)
            });
        }),
        new Promise(function (resolve,reject) {
          //协助负责人数据
          var asistOperator = vm.users.filter(function(val){
            if(val.ischecked){
              val.aid = {
                questionId:vm.questionId,
                operatorCode:val.usercode
              }
              return val;
            }
          });
          QuestionAPI.assistOperator({osId:$stateParams.osId},asistOperator)
            .$promise
            .then(function(res){
              resolve(res)
            });

        })])
        .then(function (res) {
          $uibModalInstance.close(res)

        })

    }
    function isSelected(user) {
      user.isChecked = !user.isChecked;

    }

  }
})();

