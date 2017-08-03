(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionViewController', QuestionViewController)

  /** @ngInject */
  function QuestionViewController($stateParams, $state,QuestionAPI,RoundAPI) {
    let vm = this;
    vm.askOthers = askOthers;

    activate()

    function activate() {
      getQuestion();
      getRound(Object.assign({},$stateParams));
    }

    //////////////////////////

    function getQuestion() {
      return QuestionAPI.get($stateParams)
        .$promise
        .then(res => vm.question = res)
    }
    function getRound(params) {
      delete params.roundId;
      return RoundAPI.query(params)
        .$promise
        .then(res => vm.rounds= res)
    }
    function askOthers() {
      $state.go('root.question.edit',{catalogId:$stateParams.catalogId})
    }
  }
})();

