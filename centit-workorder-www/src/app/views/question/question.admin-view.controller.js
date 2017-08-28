(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionAdminViewController', QuestionAdminViewController)

  /** @ngInject */
  function QuestionAdminViewController($stateParams,ConfirmModalService,$uibModal,toastr,QuestionAPI,RoundAPI) {
    let vm = this;

    vm.showUser = 'F';
    vm.reply = reply;
    vm.assignMe = assignMe;
    vm.assign = assign;
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
        .then(function(val){
          vm.rounds.push(val);
          vm.questionRound.roundContent='';
        })
    }

    function assignMe() {
      ConfirmModalService.openModal("确定分配给自己吗？")
        .then(function () {
          QuestionAPI.grab($stateParams,{})
            .$promise
            .then(function (val) {
              vm.question.questionState = 'H';//已分配
              vm.rounds.push(val);
            });
        })

    }

    function changeUserTag(round) {
      let showUser=round.showUser
      let roundId = round.roundId

      RoundAPI.changeUserTag(Object.assign({roundId,showUser},$stateParams),{})
        .$promise
        .then(function () {
          round.showUser = showUser;
        });

    }



    function assign() {
      QuestionAPI.listAssistOperator({osId:$stateParams.osId,questionId:vm.question.questionId})
        .$promise
        .then(function (val) {
          $uibModal.open({
            templateUrl: 'app/views/question/question-assign.html',
            controller: 'QuestionAssignController',
            controllerAs: 'vm',
            resolve: {
              questionId:function(){
                return vm.question.questionId;
              },
              assistOperator:function () {
                return val;
              },
              type:function () {
                return 'assist';//只分配协助负责人
              }
            }
          }).result
            .then(function() {
              toastr.success(`分配成功`);
            })

        })



    }

  }
})();

