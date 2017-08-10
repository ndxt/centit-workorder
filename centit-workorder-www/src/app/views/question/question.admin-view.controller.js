(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionAdminViewController', QuestionAdminViewController)

  /** @ngInject */
  function QuestionAdminViewController($stateParams,QuestionAPI,RoundAPI) {
    let vm = this;
    vm.reply = reply;

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
    function reply(showUser){

      RoundAPI
        .reply({questionId:$stateParams.questionId},Object.assign(vm.questionRound,{showUser:showUser}))
        .$promise
        .then(function(){
          vm.questionRound.roundContent='';
          vm.rounds = getRound(Object.assign({},$stateParams));
        })
    }

  }
})();

