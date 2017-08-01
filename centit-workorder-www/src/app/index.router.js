(function() {
  'use strict';

  angular
    .module('workorder')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider

      // 默认布局
      .state('root', {
        abstract: true,
        url: '/os/:osId',
        views: {
          header: {
            templateUrl: 'app/views/commons/header.html'
          }
        },
        data: {
          requireLogin: true,
          requireAuthentication: true
        }
      })

      // 无需登录校验的布局：登录页面、401\404\500页面
      .state('blank', {
        abstract: true,
        url: '',
        views: {
          header: ''
        },
        data: {
          requireLogin: false,
          requireAuthentication: false
        }
      })

    $urlRouterProvider.otherwise(function($injector, $location){
      // 首次登录
      if (!$location.$$path) {
        return '/login'
      }

      return '/404'
    })
  }

})();
