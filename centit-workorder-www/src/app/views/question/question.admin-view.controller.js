(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionAdminViewController', QuestionAdminViewController)

  /** @ngInject */
  function QuestionAdminViewController($stateParams,ConfirmModalService,QuestionAPI,RoundAPI) {
    let vm = this;

    vm.showUser = 'F';
    vm.reply = reply;
    vm.assignMe = assignMe;
    vm.changeUserTag = changeUserTag;

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
      let round = Object.assign(vm.questionRound,{showUser:showUser?showUser:(vm.showUser?vm.showUser:'F')});

      RoundAPI
        .reply({questionId:$stateParams.questionId},round)
        .$promise
        .then(function(){
          vm.rounds.push(Object.assign({orA:'A'},vm.questionRound));
          vm.questionRound.roundContent='';
        })
    }

    function assignMe() {
      QuestionAPI.grab($stateParams,{})
        .$promise
        .then(function (res) {
          vm.question.questionState = 'H';//已分配
          vm.rounds.push({
            orA:'A',
            roundContent:'你好，已经为您的问题分配工程师，请耐心等待',
            showUser:'T'
          })
        });
    }

    function changeUserTag(round,showUser) {
      let message;
      if(showUser=='T'){
        message = '确定修改为用户可见吗？'
      }else{
        message = '确定修改为用户不可见吗？'
      }
      ConfirmModalService.openModal(message)
        .then(function () {
          let roundId = round.roundId
          RoundAPI.changeUserTag(Object.assign({roundId,showUser},$stateParams),{})
            .$promise
            .then(function () {
              round.showUser = showUser;
            });
        })

    }

  }
})();

