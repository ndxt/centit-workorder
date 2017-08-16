(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionAdminController', QuestionAdminController);

  /** @ngInject */
  function QuestionAdminController($stateParams,$state,$uibModal,toastr, QuestionAPI) {
    let vm = this;

    vm.view = view;
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
    function del(row) {
      QuestionAPI.delete(Object.assign($stateParams,{ questionId: row.questionId }))
        .$promise
        .then(function(){
          queryQuestions(Object.assign({},$stateParams))
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
  }
})();

