;(function () {
  'use strict'

  angular.module('centit.helper')
    .factory('HttpAjaxInterceptor', HttpAjaxInterceptor)

  /** @ngInject */
  function HttpAjaxInterceptor () {
    return {
      request: function (config) {
        config.headers['X-Requested-With'] = 'XMLHttpRequest'
        return config
      }
    }
  }
})();
