(function () {
  'use strict'

  angular.module('centit.helper')
    .factory('HttpResponseErrorInterceptor', HttpResponseErrorInterceptor)

  /** @ngInject */
  function HttpResponseErrorInterceptor ($q, ajaxErrorHelper) {

    return {
      responseError: function (rejection) {
        ajaxErrorHelper.showError(rejection)
        return $q.reject(rejection)
      }
    }
  }
})();
