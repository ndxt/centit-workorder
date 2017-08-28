(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionAssignController', QuestionAssignController)

  /** @ngInject */
  function QuestionAssignController($stateParams,$uibModalInstance,QuestionAPI,questionId,assistOperator,type) {
    let vm = this;

    vm.cancel = $uibModalInstance.dismiss
    vm.ok = submit
    vm.questionId = questionId
    vm.assistOperator = assistOperator
    vm.type = type



    active()

    function active() {
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
      if(vm.assistOperator && vm.assistOperator.length>0){
        vm.users.forEach(function (val) {
          for (let i=0;i<vm.assistOperator.length;i++){
            if(val.usercode==vm.assistOperator[i].aid.operatorCode){
              val.isChecked = true;
              break;
            }
          }
        })
      }
    }

    function submit() {
      Promise.all([new Promise(function(resolve){
        if(vm.type == 'all'){
          //主负责人数据
          QuestionAPI.updateOperator(Object.assign({questionId:vm.questionId},$stateParams),
            {questionId:vm.questionId,currentOperator:vm.currentOperator})
            .$promise
            .then(function(res){
              resolve(res)
            });
        }
        }),
        new Promise(function (resolve) {
          //协助负责人数据
          var asistOperator = vm.users.filter(function(val){
            if(val.isChecked){
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

  }
})();

