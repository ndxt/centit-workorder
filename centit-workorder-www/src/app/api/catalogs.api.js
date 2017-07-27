(function() {
  'use strict'

  angular.module('workorder')
    .factory('CatalogAPI', CatalogAPI)

  /** @ngInject */
  function CatalogAPI($resource, config) {

    const baseUrl = `${config.contextPath}/os/:osId/catalogs`

    return $resource(`${baseUrl}/:catalogId`, null, {})
  }
})();

