(function() {
  'use strict'

  angular.module('workorder')
    .controller('QuestionModalController', QuestionModalController)

  /** @ngInject */
  function QuestionModalController(
    $stateParams,
    $uibModalInstance,
    QuestionAPI,
    CatalogAPI,
    catalog,
    question
  ) {
    let vm = this

    // 问题
    vm.info = question || Object.assign({}, $stateParams)

    vm.catalog = catalog
    vm.cancel = $uibModalInstance.dismiss
    vm.ok = vm.info.questionId ? modifyQuestion : addQuestion

    activate()

    function activate() {
      // 如果没有制定类型，查找所有类型供选择
      if (!catalog) {
        queryCatalogs()
      }
    }

    /**
     * 新增问题
     * @param info
     * @returns {*|Promise.<TResult>}
     */
    function addQuestion(info) {
      return QuestionAPI.save({
        osId: info.osId
      }, info)
        .$promise
        .then(res => $uibModalInstance.close(res))
    }

    /**
     * 修改问题
     * @param info
     * @returns {*|Map}
     */
    function modifyQuestion(info) {
      return QuestionAPI.update({
        osId: info.osId,
        questionId: info.questionId
      }, info)
    }

    /**
     * 查询分类列表
     * @returns {*|Promise.<TResult>}
     */
    function queryCatalogs() {
      return CatalogAPI.query($stateParams)
        .$promise
        .then(res => vm.catalogs = res)
    }
  }
})();

