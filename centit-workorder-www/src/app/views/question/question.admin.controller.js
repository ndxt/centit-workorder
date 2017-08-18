(function() {
  'use strict'

  let app = angular.module('workorder')
  app.filter('timeFilter',function () {
    return function(inputArray,begTime,endTime){
      let outArray=[];
      if(inputArray && inputArray.length>0){
        outArray = inputArray.filter(function (val) {
          if(begTime && val.createTime<begTime)
            return;
          if(endTime && val.createTime>endTime)
            return;
          return val;
        })
      }
      return outArray;
    }
  })
  app.controller('QuestionAdminController', QuestionAdminController);

  /** @ngInject */
  function QuestionAdminController($stateParams,$state,$uibModal,ConfirmModalService,toastr, QuestionAPI) {
    let vm = this;

    vm.osId = $stateParams.osId;

    vm.view = view;
    vm.del = del;
    vm.assign = assign;
    vm.remove = remove;
    vm.changepage = changepage;

    vm.s_questionState = ''

    activate();

    function activate() {
      queryQuestions(Object.assign({},$stateParams))
    }

    ////////////////////////////

    function queryQuestions(params) {
      delete params.questionId;
      let promise = QuestionAPI.query(params).$promise

      promise.then(res => {
          console.log(promise)
          vm.questions = res
          vm.$resource = promise.$$state.value
        })
    }

    function view(row) {
      $state.go("admin.question.view",Object.assign($stateParams,{ questionId: row.questionId }));
    }
    function del(row) {
      ConfirmModalService.openModal('确定删除吗？')
        .then(function () {
          QuestionAPI.delete(Object.assign($stateParams,{ questionId: row.questionId }))
            .$promise
            .then(function(){
              queryQuestions(Object.assign({},$stateParams))
            })
        })

    }
    function assign(row) {
      $uibModal.open({
        templateUrl: 'app/views/question/question-assign.html',
        controller: 'QuestionAssignController',
        controllerAs: 'vm',
        resolve: {
          questionId:function(){
            return row.questionId;
          }
        }
      }).result
        .then(function(res) {
          toastr.success(`分配成功`);
          row.questionState = 'H';
        })

    }

    function remove(type){
      if(type=='s_questionState')
        vm.s_questionState= '';
      if(type=='s_questionTitle')
        vm.s_questionTitle= '';
      if(type=='s_createTime'){
        vm.s_begTime= '';
        vm.s_endTime='';

      }
    }

    function changepage() {
      queryQuestions(Object.assign({
        pageNo:vm.$resource.$pageDesc.pageNo,
        pageSize:20,
      },$stateParams))
      console.log(vm.currentPage);
    }

  }
})();

