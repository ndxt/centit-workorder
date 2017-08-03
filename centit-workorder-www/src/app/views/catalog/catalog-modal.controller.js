(function() {
  'use strict'

  angular.module('workorder')
    .controller('CatalogModalController', CatalogModalController)

  /** @ngInject */
  function CatalogModalController(
    $uibModalInstance,
    CatalogAPI,
    catalog,
    os
  ) {
    const vm = this

    vm.info = angular.extend({}, catalog, {
      osId: os.osId
    })

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
        .then(res => $uibModalInstance.close(res))
    }

    /**
     * 修改类型
     * @param info
     * @returns {*|Promise.<TResult>}
     */
    function modifyQuestion(info) {

      console.log(info)

      return CatalogAPI.update({
        osId: os.osId,
        catalogId: info.catalogId
      }, info)
    }
  }
})();

