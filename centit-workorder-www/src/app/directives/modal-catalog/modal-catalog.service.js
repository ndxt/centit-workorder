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
            return catalogResolve(os, catalog)
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
     * @param osId
     */
    function osResolve(osId) {

      return OsAPI.get({ osId })

      if (!os) {
        return $q.reject('os不能为空')
      }

      if (angular.isString(os)) {
        return OsAPI.get({
          osId: os
        }).$promise
          .then(res => {
            if (!res) {
              return $q.reject('os不能为空')
            }
            return res
          })
      }

      return $q.resolve(os)
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
      })
    }
  }
})()
