(function() {
  'use strict'

  angular.module('workorder')
    .factory('DocAPI', DocAPI)

  /** @ngInject */
  function DocAPI($resource, config) {
    const baseUrl = `${config.contextPath}/service/os/:osId/documents`

    const editContent = {
      url: `${baseUrl}/:docId/content`,
      method: 'PUT'
    }

    return $resource(`${baseUrl}/:docId`, null, {
      editContent,
      levelSearch: {
        url: `${config.contextPath}/service/os/:osId/documents/levelSearch`,
        isArray: true,
        method: 'GET'
      },
      score:{
        url:`${config.contextPath}/service/os/:osId/documents/:docId/score`,
        method:'POST'
      }
    })
  }
})();
