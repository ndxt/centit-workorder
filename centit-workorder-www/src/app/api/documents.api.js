(function() {
  'use strict'

  angular.module('workorder')
    .factory('DocAPI', DocAPI)

  /** @ngInject */
  function DocAPI($resource, config) {
    const baseUrl = `${config.contextPath}/os/:osId/documents`

    return $resource(`${baseUrl}/:docId`, null, {
      listTree: {
        url: `${config.contextPath}/os/:osId/documents/treeSearch`,
        isArray: true,
        method: 'GET'
      }
    })
  }
})();
