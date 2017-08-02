(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionViewController', QuestionViewController)

  /** @ngInject */
  function QuestionViewController($stateParams, QuestionAPI,RoundAPI) {
    let vm = this

    activate()

    function activate() {
      getQuestion();
      getRound();
    }

    //////////////////////////

    function getQuestion() {
      return QuestionAPI.get($stateParams)
        .$promise
        .then(res => vm.question = res.questionInfo)
    }
    function getRound() {
      return RoundAPI.get($stateParams)
        .$promise
        .then(res => vm.rounds)
    }
  }
})();

