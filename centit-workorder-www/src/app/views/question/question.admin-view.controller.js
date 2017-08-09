(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionAdminViewController', QuestionAdminViewController)

  /** @ngInject */
  function QuestionAdminViewController($stateParams,QuestionAPI,RoundAPI) {
    let vm = this;
    vm.apply = apply;

    activate()

    function activate() {
      vm.question = getQuestion();
      vm.rounds = getRound(Object.assign({},$stateParams));
    }

    //////////////////////////

    function getQuestion() {
      return QuestionAPI.get($stateParams)
    }
    function getRound(params) {
      delete params.roundId;
      return RoundAPI.query(params)
    }
    function apply(){

      RoundAPI
        .supplemental({questionId:$stateParams.questionId},{questionRound:vm.questionRound})
        .$promise
        .then(function(){
          vm.questionRound.roundContent='';
          vm.rounds = getRound(Object.assign({},$stateParams));
        })
    }

  }
})();

