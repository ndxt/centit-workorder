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
      return CatalogAPI.get($stateParams)
        .$promise
        .then(res => vm.catalog = res)
    }

    /**
     * 查询文档信息列表
     * @returns {Promise.<TResult>|*}
     */
    function queryDocument(params) {
      delete params.DocId;
      return DocAPI.query(params)
        .$promise
        .then(res => vm.documents = res)
    }

    function askOthers() {
      $state.go('root.question.edit',{catalogId:$stateParams.catalogId})
    }
  }
})();
