(function() {
  'use strict'

  angular.module('workorder')
    .factory('CatalogAPI', CatalogAPI)

  /** @ngInject */
  function CatalogAPI($resource, config) {

    const baseUrl = `${config.contextPath}/service/os/:osId/catalogs/:catalogId`

    const update = {
      method: 'PUT'
    }
    const rankList = {
      url:`${baseUrl}/rankList`,
      method: 'GET',
      isArray:true
    }

    return $resource(baseUrl, null, {
      update,rankList
    })

  }
})();

