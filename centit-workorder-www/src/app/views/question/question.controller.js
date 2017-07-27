(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionController', QuestionController)

  /** @ngInject */
  function QuestionController($stateParams, QuestionAPI) {
    let vm = this

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
  }
})();

