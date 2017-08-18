(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionController', QuestionController);

  /** @ngInject */
  function QuestionController($stateParams,$state,ConfirmModalService, QuestionAPI) {
    let vm = this;

    vm.osId = $stateParams.osId;
    vm.view = view;
    vm.edit = edit;
    vm.del = del;

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

