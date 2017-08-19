(function() {
  'use strict'

  angular.module('workorder')
    .filter('timeFilter',function () {
      return function(inputArray,begTime,endTime){
        let outArray=[];
        if(inputArray && inputArray.length>0){
          outArray = inputArray.filter(function (val) {
            let createTime = new Date(val.createTime)

            if(begTime && createTime<begTime)
              return;
            if(endTime && createTime>endTime)
              return;
            return val;
          })
        }
        return outArray;
      }
    }).filter('pageSize', function () {
      return function(inputArray, currentPage = 1, pageSize = 10){
        if (!inputArray) return []
        let start = (currentPage - 1) * pageSize
        return inputArray.slice(start, start + pageSize)
      }
    }).controller('QuestionAdminController', QuestionAdminController)

  /** @ngInject */
  function QuestionAdminController($stateParams, $state,$uibModal,ConfirmModalService,toastr, QuestionAPI) {
    let vm = this;

    vm.osId = $stateParams.osId;

    vm.view = view;
    vm.del = del;
    vm.assign = assign;
    vm.remove = remove;

    vm.s_questionState = ''

    activate();

    function activate() {
      queryQuestions(Object.assign({},$stateParams))
    }

    ////////////////////////////

    function queryQuestions(params) {
      delete params.questionId;
      vm.questions  = QuestionAPI.query(params)
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
        .then(function() {
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

  }
})();

