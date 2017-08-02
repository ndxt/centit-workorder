(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionController', QuestionController)

  /** @ngInject */
  function QuestionController($stateParams,$state, QuestionAPI) {
    let vm = this

    vm.view = view;
    vm.edit = edit;
    vm.del = del;

    activate()

    function activate() {
      queryQuestions($stateParams)
    }

    ////////////////////////////

    function queryQuestions(params) {
      return QuestionAPI.query(params)
        .$promise
        .then(res => {
          vm.questions = res
          vm.totalItems = res.length || 0
        })
    }

    function view(row) {
      $state.go("root.question.view",{ questionId: row.questionId });

    }
    function edit(row) {
      $state.go("root.question.edit",{ questionId: row.questionId });

    }
    function del(row) {
      QuestionAPI.delete(Object.assign($stateParams,{ questionId: row.questionId }));
    }
  }
})();

