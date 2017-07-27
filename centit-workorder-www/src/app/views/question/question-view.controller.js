(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionViewController', QuestionViewController)

  /** @ngInject */
  function QuestionViewController($stateParams, QuestionAPI) {
    let vm = this

    activate()

    function activate() {
      getQuestion()
    }

    //////////////////////////

    function getQuestion() {
      return QuestionAPI.get($stateParams)
        .$promise
        .then(res => vm.question = res)
    }
  }
})();

