(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionAdminController', QuestionAdminController);

  /** @ngInject */
  function QuestionAdminController($stateParams,$state,$uibModal, QuestionAPI) {
    let vm = this;

    vm.view = view;
    vm.edit = edit;
    vm.del = del;
    vm.assign = assign

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
      $state.go("admin.question.view",Object.assign($stateParams,{ questionId: row.questionId }));
    }
    function edit(row) {
      $state.go("admin.question.view",Object.assign($stateParams,{ questionId: row.questionId }));
    }
    function del(row) {
      QuestionAPI.delete(Object.assign($stateParams,{ questionId: row.questionId }))
        .$promise
        .then(function(){
          queryQuestions(Object.assign({},$stateParams))
        })

    }
    function assign() {
      $uibModal.open({
        templateUrl: 'app/views/question/question-assign.html',
        controller: 'QuestionAssignController',
        controllerAs: 'vm',
      }).result
        .then(function() {
          toastr.success(`评论成功`)
        })

    }
  }
})();

