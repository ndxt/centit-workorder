(function() {
  'use strict';

  angular
    .module('workorder')
    .config(config)
    .config(toastConfig)
    .config(routerHelperConfig)
    .config(localStorageCofnig)

  /** @ngInject */
  function localStorageCofnig(localStorageServiceProvider) {
    localStorageServiceProvider
      .setPrefix('workorder')
      .setDefaultToCookie(false)
  }

  /** @ngInject */
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
})();
