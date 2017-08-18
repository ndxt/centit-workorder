(function() {
  'use strict'

  let app = angular.module('workorder')
  app.filter('timeFilter',function () {
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
  })
  app.controller('QuestionController', QuestionController);

  /** @ngInject */
  function QuestionController($stateParams,$state,ConfirmModalService, QuestionAPI) {
    let vm = this;

    vm.osId = $stateParams.osId;
    vm.view = view;
    vm.edit = edit;
    vm.del = del;

    vm.s_questionState = ''

    activate();

    function activate() {
      queryQuestions(Object.assign({},$stateParams))
    }

    ////////////////////////////

    function queryQuestions(params) {
      delete params.questionId;
      return QuestionAPI.query(params)
        .$promise
        .then(res => {
          vm.questions = res
          vm.totalItems = res.length || 0
        })
    }

    function view(row) {
      $state.go("root.question.view",Object.assign($stateParams,{ questionId: row.questionId }));

    }
    function edit(row) {
      $state.go("root.question.edit",Object.assign($stateParams,{ questionId: row.questionId }));

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
  }
})();

