;(function () {
  'use strict'

  angular.module('centit.helper')
    .factory('HttpResponseInterceptor', HttpResponseInterceptor)

  /** @ngInject */
  function HttpResponseInterceptor ($q, ajaxErrorHelper) {
    return {
      response: function (response) {
        let config = response.config
        let result = response.data
        let code = result.code

        if (angular.isUndefined(code)) {
          return response
        }

        if (code === 0) {
          let data = result.data

          if (angular.isUndefined(data.objList)) {
            response.data = data
          }
          else {
            response.data = data.objList
          }


        } else {
          let rejection = {
            config,
            status: code,
            statusText: result.message
          }

          ajaxErrorHelper.showError(rejection)
          return $q.reject(rejection)
        }

        return response
      }
    }
  }
})()
