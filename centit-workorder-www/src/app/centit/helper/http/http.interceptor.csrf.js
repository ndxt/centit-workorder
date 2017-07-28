;(function () {
  'use strict'

  angular.module('centit.helper')
    .factory('HttpCsrfInterceptor', HttpCsrfInterceptor)

  /** @ngInject */
  function HttpCsrfInterceptor (System) {
    return {
      request: function (config) {
        let method = config.method
        let params = config.params

        if (['POST', 'PUT', 'DELETE'].includes(method)) {
          config.params = Object.assign({}, params, System.csrf)
        }
        return config
      }
    }
  }
})()
