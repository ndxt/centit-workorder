(function () {
  'use strict'

  angular.module('centit.helper')
    .factory('HttpResponseInterceptor', HttpResponseInterceptor)

  /** @ngInject */
  function HttpResponseInterceptor ($q, $timeout, ajaxErrorHelper) {
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

          if (!data) return {}

          if (angular.isUndefined(data.objList)) {
            response.data = data
          }
          else {
            response.data = data.objList

            // 确保分页信息能正确传递
            $timeout(() => response.resource.$pageDesc = data.pageDesc)
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
})();
