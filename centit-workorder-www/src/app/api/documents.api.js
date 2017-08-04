(function() {
  'use strict'

  angular.module('workorder')
    .factory('DocAPI', DocAPI)

  /** @ngInject */
  function DocAPI($resource, config) {
    const baseUrl = `${config.contextPath}/service/os/:osId/documents`

    return $resource(`${baseUrl}/:docId`, null, {
      levelSearch: {
        url: `${config.contextPath}/service/os/:osId/documents/levelSearch`,
        isArray: true,
        method: 'GET'
      }
    })
  }
})();
