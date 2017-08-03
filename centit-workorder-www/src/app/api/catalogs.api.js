(function() {
  'use strict'

  angular.module('workorder')
    .factory('CatalogAPI', CatalogAPI)

  /** @ngInject */
  function CatalogAPI($resource, config) {

    const baseUrl = `${config.contextPath}/service/os/:osId/catalogs/:catalogId`

    return $resource(baseUrl, null, {})
  }
})();

