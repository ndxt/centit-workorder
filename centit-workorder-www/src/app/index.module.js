(function() {
  'use strict';

  angular
    .module('workorder', [
      'ngAnimate',
      'ngCookies',
      'ngSanitize',
      // 'ngMockE2E',
      'ngMessages',
      'ngResource',

      // ui插件
      'ui.router',
      'ui.bootstrap',

      // 第三方插件
      'toastr',
      'angular-loading-bar',

      // 公司前端框架模块
      'centit.ui'
    ]);

})();
