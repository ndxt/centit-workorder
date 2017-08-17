(function() {
  'use strict'

  angular.module('workorder')
    .controller('CatalogModalController', CatalogModalController)

  /** @ngInject */
  function CatalogModalController(
    $uibModalInstance,
    CatalogAPI,
    catalog,
    os,
    parentId
  ) {
    const vm = this
    const ErrorMessage = '后台请求时发生错误。'

    vm.os = os
    vm.info = angular.extend({}, catalog, {
      icon:"glyphicon-home",
      osId: os.osId,
      parentId : parentId
    })
    queryCatalogs({osId: os.osId});

    vm.cancel = $uibModalInstance.dismiss
    vm.ok = vm.info.catalogId ? modifyQuestion : addQuestion

    /**
     * 新增类型
     * @param info
     * @returns {*|Promise.<TResult>}
     */
    function addQuestion(info) {
      return CatalogAPI.save({
        osId: os.osId
      }, info)
        .$promise
        .then(function (res) {
          $uibModalInstance.close(res);
        })
        .catch(() => vm.error = ErrorMessage)
    }

    /**
     * 修改类型
     * @param info
     * @returns {*|Promise.<TResult>}
     */
    function modifyQuestion(info) {
      return CatalogAPI.update({
        osId: os.osId,
        catalogId: info.catalogId
      }, info)
        .$promise
        .then(res => $uibModalInstance.close(res))
        .catch(() => vm.error = ErrorMessage)
    }

    function queryCatalogs(params) {
      delete params.catalogId;
      CatalogAPI.query(params)
        .$promise
        .then(res => vm.catalogs = res)
    };
  }
})();

