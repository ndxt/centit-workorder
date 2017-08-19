(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionViewController', QuestionViewController)

  /** @ngInject */
  function QuestionViewController($stateParams,QuestionAPI,RoundAPI) {
    let vm = this;
    vm.hoveringOver = hoveringOver;
    vm.continueAsk = continueAsk;
    vm.submitScore = submitScore;

    vm.range = function(n) {
      return new Array(n);
    }

    activate()

    function activate() {
      vm.question = getQuestion();

      vm.rounds = getRound(Object.assign({},$stateParams));
    }

    //////////////////////////

    function getQuestion() {
      return QuestionAPI.get($stateParams)
        .$promise.then(function (res) {
          vm.question = res;

        })
    }
    function getRound(params) {
      delete params.roundId;
      return RoundAPI.listT(params)
    }
    function continueAsk(){

      RoundAPI
        .supplemental({questionId:$stateParams.questionId},vm.questionRound)
        .$promise
        .then(function(){
          vm.questionRound.roundContent='';
          vm.rounds.push(vm.questionRound);
        })
    }
    function submitScore(){
      QuestionAPI.comment(Object.assign({evaluateScore:vm.rate},$stateParams),{})
        .$promise
        .then(function(){
          activate();
        });
    }

    //评分
    vm.rate =0;

    function hoveringOver (value) {
      vm.overStar = value;
      if(vm.overStar<3)
        vm.showMsg='很差';
      else if(vm.overStar<5)
        vm.showMsg = '一般';
      else
        vm.showMsg='很好';
    }
  }
})();

