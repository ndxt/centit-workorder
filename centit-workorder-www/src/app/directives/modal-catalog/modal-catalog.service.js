(function() {
  'use strict'

  angular.module('workorder')
    .factory('CatalogModalService', CatalogModalService)

  /** @ngInject */
  function CatalogModalService(
    $q,
    $uibModal,
    toastr,
    OsAPI,
    CatalogAPI
  ) {

    return {
      openModal
    }

    ///////////////////////////////////////////////

    function openModal(os, catalog,parentId,freshData) {
      return $uibModal.open({
        templateUrl: 'app/views/catalog/catalog-modal.html',
        controller: 'CatalogModalController',
        controllerAs: 'vm',
        resolve: {
          os: function() {
            return osResolve(os)
            // return {
            //   osId: 'N002'
            // }
          },
          catalog: function() {
            return catalogResolve(os, catalog)
          },
          parentId: function() {
            return parentId
          }
        }
      }).result
        .then(function(obj) {
          // 新增
          if (!catalog || !catalog.catalogId) {
            toastr.success(`类型【${obj.catalogName}】创建成功`)
          }
          else {
            toastr.success(`类型【${obj.catalogName}】修改成功`)
          }

          if(freshData)
            freshData();
          return obj
        })
    }

    /**
     * 获取系统对象
     * @param osId
     */
    function osResolve(osId) {
      if (!osId) {
        return $q.reject('os不能为空')
      }
      return OsAPI.get({
        osId
      }).$promise
    }

    /**
     * 获取类型对象
     * @param osId
     * @param catalogId
     * @returns {*}
     */
    function catalogResolve(osId, catalogId) {

      if (!catalogId) {
        return $q.resolve({ osId })
      }

      return CatalogAPI.get({
        osId,
        catalogId
      }).$promise
    }
  }
})();
