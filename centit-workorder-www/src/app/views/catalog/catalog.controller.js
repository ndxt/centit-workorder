(function() {
  'use strict'

  angular.module('workorder')
    .controller('CatalogController', CatalogController)

  /** @ngInject */
  function CatalogController($stateParams,$state,CatalogAPI,DocAPI) {

    let vm = this;
    vm.askOthers = askOthers;

    activate()

    function activate() {
      getCatalog()
      queryDocument(Object.assign({},$stateParams))
    }

    ///////////////////////////////////////

    /**
     * 获取分类信息详情
     * @returns {Promise.<TResult>|*}
     */
    function getCatalog() {
      return vm.catalog = CatalogAPI.get($stateParams)
    }

    /**
     * 查询文档信息列表
     * @returns {Promise.<TResult>|*}
     */
    function queryDocument(params) {
      delete params.DocId;
      return vm.documents = DocAPI.query(params)
    }

    function askOthers() {
      $state.go('root.question.add',{catalogId:vm.catalog.catalogId})
    }
  }
})();
