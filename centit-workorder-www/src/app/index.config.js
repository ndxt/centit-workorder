(function() {
  'use strict';

  angular
    .module('workorder')
    .config(config)
    .config(mockConfig)
    .config(toastConfig)
    .config(routerHelperConfig)

  function toastConfig(toastrConfig) {
    Object.assign(toastrConfig, {
      maxOpened: 1,
      preventDuplicates: false
    })
  }

  /** @ngInject */
  function routerHelperConfig (routerHelperProvider) {
    routerHelperProvider.configure({
      mainTitle: '工单系统'
    })
  }

  /** @ngInject */
  function config($logProvider, toastrConfig) {
    // Enable log
    $logProvider.debugEnabled(true);

    // Set options third-party lib
    toastrConfig.allowHtml = true;
    toastrConfig.timeOut = 3000;
    toastrConfig.positionClass = 'toast-top-right';
    toastrConfig.preventDuplicates = true;
    toastrConfig.progressBar = true;
  }

  /** @ngInject */
  function mockConfig ($httpProvider) {
    $httpProvider.interceptors.push(apiDelayInterceptor);

    /* @ngInject */
    function apiDelayInterceptor ($timeout, $q) {
      return {
        'response': function (response) {
          // all API response will be delayed 1s to simulate real network
          let delay = 100 + Math.random() * 900;
          if (response.config.url.match(/^api\//)) {
            let d = $q.defer();
            $timeout(function () {
              d.resolve(response);
            }, delay);
            return d.promise;
          }
          return response;
        }
      };
    }
  }

})();
