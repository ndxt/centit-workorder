(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionAdminViewController', QuestionAdminViewController)

  /** @ngInject */
  function QuestionAdminViewController($stateParams,QuestionAPI,RoundAPI) {
    let vm = this;

    vm.showUser = 'F';
    vm.reply = reply;
    vm.assignMe = assignMe;

    activate()

    function activate() {
      vm.question = getQuestion();
      vm.rounds = getRound(Object.assign({},$stateParams));
    }

    //////////////////////////

    function getQuestion() {
      return QuestionAPI.allQuestionInfo($stateParams)
        .$promise
        .then(function(res){
          vm.question = res.data.object;
          vm.isOperate = res.data.role;
        })
    }
    function getRound(params) {
      delete params.roundId;
      return RoundAPI.query(params)
    }
    function reply(showUser){

      RoundAPI
        .reply({questionId:$stateParams.questionId},Object.assign(vm.questionRound,{showUser:showUser?showUser:(vm.showUser?vm.showUser:'F')}))
        .$promise
        .then(function(){
          vm.questionRound.roundContent='';
          vm.rounds = getRound(Object.assign({},$stateParams));
        })
    }

    function assignMe() {
      QuestionAPI.grab($stateParams,{});

    }

  }
})();

