(function() {
  'use strict'

  angular.module('workorder')
    .filter('timeFilter',function () {
      return function (inputArray, tag,times) {
        let begTime;
        let endTime;
        if(tag =='TM'){
          begTime = times.t1;
          endTime = times.t2;
        }
        if(tag =='LM'){
          begTime = times.t3;
          endTime = times.t4;
        }
        if(tag =='TS'){
          begTime = times.t5;
          endTime = times.t6;
        }
        if(tag =='LS'){
          begTime = times.t7;
          endTime = times.t8;
        }
        if(tag =='TY'){
          begTime = times.t9;
          endTime = times.t10;
        }
        if(tag =='LY'){
          begTime = times.t11;
          endTime = times.t12;
        }
        begTime = new Date(begTime);
        endTime = new Date(endTime);
        let outArray = [];
        if (inputArray && inputArray.length > 0) {
          outArray = inputArray.filter(function (val) {
            let createTime = new Date(val.createTime)

            if (begTime && createTime < begTime)
              return;
            if (endTime && createTime > endTime)
              return;
            return val;
          })
        }
        return outArray;
      }
    }).filter('pageSize', function () {
      return function(inputArray, currentPage = 1, pageSize = 10){
        if (!inputArray) return []
        let start = (currentPage - 1) * pageSize
        return inputArray.slice(start, start + pageSize)
      }
    }).controller('QuestionAdminController', QuestionAdminController)

  /** @ngInject */
  function QuestionAdminController($stateParams, $state,$uibModal,ConfirmModalService,DateService,toastr, QuestionAPI) {
    let vm = this;

    vm.osId = $stateParams.osId;

    vm.view = view;
    vm.del = del;
    vm.assign = assign;
    vm.remove = remove;


    vm.s_questionState = '';
    vm.currentPage=1

    //月
    let t1 =  DateService.getMonthStartDate();//本月开始时间
    let t2 =  DateService.getMonthEndDate();//本月开始时间
    let t3 =  DateService.getLastMonthStartDate();//本月开始时间
    let t4  =  DateService.getLastMonthEndDate();//本月开始时间
    //季度
    let t5 =  DateService.getQuarterStartDate();//本月开始时间
    let t6 =  DateService.getQuarterEndDate();//本月开始时间
    let t7 =  DateService.getLastQuarterStartDate();//本月开始时间
    let t8 =  DateService.getLastQuarterEndDate();//本月开始时间
    //年
    let t9 =  DateService.getYearStartDate();//本月开始时间
    let t10 =  DateService.getYearEndDate();//本月开始时间
    let t11 =  DateService.getLastYearStartDate();//本月开始时间
    let t12 =  DateService.getLastYearEndDate();//本月开始时间
    vm.getMonth = {t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12};

    activate();
    function activate() {
      queryQuestions(Object.assign({},$stateParams))
    }

    ////////////////////////////

    function queryQuestions(params) {
      delete params.questionId;
      vm.questions  = QuestionAPI.query(params)
    }

    function view(row) {
      $state.go("admin.question.view",Object.assign($stateParams,{ questionId: row.questionId }));
    }
    function del(row) {
      ConfirmModalService.openModal('确定删除吗？')
        .then(function () {
          QuestionAPI.delete(Object.assign($stateParams,{ questionId: row.questionId }))
            .$promise
            .then(function(){
              queryQuestions(Object.assign({},$stateParams))
            })
        })

    }
    function assign(row) {
      $uibModal.open({
        templateUrl: 'app/views/question/question-assign.html',
        controller: 'QuestionAssignController',
        controllerAs: 'vm',
        resolve: {
          questionId:function(){
            return row.questionId;
          }
        }
      }).result
        .then(function() {
          toastr.success(`分配成功`);
          row.questionState = 'H';
        })

    }

    function remove(type){
      if(type=='s_questionState')
        vm.s_questionState= '';
      if(type=='s_questionTitle')
        vm.s_questionTitle= '';
      if(type=='s_timeTag'){
        vm.s_timeTag= '';
      }
    }

  }
})();

