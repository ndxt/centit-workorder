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

    function openModal(os, catalog) {
      return $uibModal.open({
        templateUrl: 'app/views/catalog/catalog-modal.html',
        controller: 'CatalogModalController',
        controllerAs: 'vm',
        resolve: {
          os: function() {
            return osResolve(os)
          },
          catalog: function() {
            return catalogResolve(catalog)
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

          return obj
        })
    }

    /**
     * 获取系统对象
     * @param os
     */
    function osResolve(os) {
      if (!os) {
        return $q.reject('os不能为空')
      }

      if (angular.isString(os)) {
        return OsAPI.get({
          osId: os
        }).$promise
          .then(res => {
            if (!res) {
              throw Error('os不能为空')
            }
            return res
          })
      }

      return $q.resolve(os)
    }

    function catalogResolve(catalog) {
      if (angular.isString(catalog)) {
        return CatalogAPI.get({
          catalogId: catalog
        }).$promise
      }

      return $q.resolve(catalog)
    }
  }
})()
