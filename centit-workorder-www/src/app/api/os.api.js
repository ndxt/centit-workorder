(function() {
  'use strict'

  angular.module('workorder')
    .factory('OsAPI', OsAPI)

  /** @ngInject */
  function OsAPI($resource, config) {

    const baseUrl = `${config.contextPath}/os/:osId`

    return $resource(baseUrl, null, {})
  }
})()

