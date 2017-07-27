(function() {
  'use strict'

  angular.module('workorder')
    .controller('CatalogController', CatalogController)

  /** @ngInject */
  function CatalogController(
    $stateParams,
    CatalogAPI,
    DocAPI
  ) {

    let vm = this

    activate()

    function activate() {
      getCatalog()
      queryDocument()
    }

    ///////////////////////////////////////

    /**
     * 获取分类信息详情
     * @returns {Promise.<TResult>|*}
     */
    function getCatalog() {
      return CatalogAPI.get($stateParams)
        .$promise
        .then(res => vm.catalog = res)
    }

    /**
     * 查询文档信息列表
     * @returns {Promise.<TResult>|*}
     */
    function queryDocument() {
      return DocAPI.query($stateParams)
        .$promise
        .then(res => vm.documents = res)
    }
  }
})();
