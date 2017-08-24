(function () {
  'use strict'

  angular.module('workorder')
    .controller('QuestionEditController', QuestionEditController)

  /** @ngInject */
  function QuestionEditController($window, $stateParams, $state, QuestionAPI, CatalogAPI, question) {
    let vm = this;
    vm.cancel = cancel;
    vm.ok = $stateParams.questionId ? modifyQuestion : addQuestion;
    vm.question = question
    activate();

    function activate() {
      queryCatalogs(Object.assign({}, $stateParams));
    }

    //////////////////////////

    function queryCatalogs(params) {
      delete params.catalogId;
      CatalogAPI.query(params)
        .$promise
        .then(res => vm.catalogs = res)
    }

    function cancel() {
      $window.history.back(-1);
    }


    /**
     * 新增问题
     * @returns {*|Promise.<TResult>}
     */
    function addQuestion() {
      return QuestionAPI.save({osId: $stateParams.osId}, vm.question)
        .$promise
        .then(function () {
          $state.go('root.question', $stateParams)
        })
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
        .$promise
        .then(function () {
          $state.go('root.question', $stateParams)
        })
    }
  }
})();

