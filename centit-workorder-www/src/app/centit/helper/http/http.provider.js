;(function () {

  angular.module('centit.helper')
    .provider('httpHelper', httpHelperProvider)

  /** @ngInject */
  function httpHelperProvider ($httpProvider) {

    const config = {
      csrf: true
    }

    this.configure = function (cfg) {
      Object.assign(config, cfg)
    }

    init()

    this.$get = HttpHelper
    /** @ngInject */
    function HttpHelper () {
      return {
        addInterceptor
      }
    }

    function addInterceptor (interceptor) {
      $httpProvider.interceptors.push(interceptor)
    }

    function init () {
      if (config.csrf) {
        addInterceptor('HttpCsrfInterceptor')
      }

      addInterceptor('HttpAjaxInterceptor')
      addInterceptor('HttpResponseInterceptor')
      addInterceptor('HttpResponseErrorInterceptor')
    }
  }
})()
