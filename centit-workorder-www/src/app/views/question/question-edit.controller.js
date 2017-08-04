(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionEditController', QuestionEditController)

  /** @ngInject */
  function QuestionEditController($stateParams,QuestionAPI,CatalogAPI) {
    let vm = this;
    vm.cancel = cancel;
    vm.ok = $stateParams.questionId ? modifyQuestion : addQuestion;

    activate();

    function activate() {
      if($stateParams.questionId){
        getQuestion();
      }
      queryCatalogs(Object.assign({},$stateParams));
    }

    //////////////////////////

    function getQuestion() {
      return QuestionAPI.get($stateParams)
        .$promise
        .then(res => vm.question = res)
    }

    function queryCatalogs(params) {
      delete params.catalogId;
      CatalogAPI.query(params)
        .$promise
        .then(res => vm.catalogs = res)
    };

    function cancel(){
      window.history.back(-1);
    };


    /**
     * 新增问题
     * @param info
     * @returns {*|Promise.<TResult>}
     */
    function addQuestion() {
      return QuestionAPI.save({osId: $stateParams.osId}, vm.question)
    }

    /**
     * 修改问题
     * @param info
     * @returns {*|Map}
     */
    function modifyQuestion() {
      return QuestionAPI.update({
        osId: $stateParams.osId,
        questionId: $stateParams.questionId
      }, vm.question)
    }
  }
})();

