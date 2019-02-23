(function() {
  'use strict'

  angular.module('workorder')
    .factory('DocAPI', DocAPI)

  /** @ngInject */
  function DocAPI($resource, config) {
    const baseUrl = `${config.contextPath}/workorder/os/:osId/documents`

    const editContent = {
      url: `${baseUrl}/:docId/content`,
      method: 'PUT'
    }
    const fullSearch = {
      url: `${baseUrl}/fullSearch/:keyWord`,
      method: 'GET',
      isArray:true
    }
    const fullTextSearch = {
      url: `${baseUrl}/fullTextSearch/:catalogId`,
      method: 'GET',
      isArray:true
    }
    const update = {
      method: 'PUT'
    }

    return $resource(`${baseUrl}/:docId`, null, {
      editContent,fullSearch,fullTextSearch,update,
      levelSearch: {
        url: `${config.contextPath}/workorder/os/:osId/documents/levelSearch`,
        isArray: true,
        method: 'GET'
      },
      score:{
        url:`${config.contextPath}/workorder/os/:osId/documents/:docId/score`,
        method:'POST'
      }
    })
  }
})();
