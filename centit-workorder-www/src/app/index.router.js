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
        url: '/user/:osId',
        views: {
          header: {
            templateUrl: 'app/views/commons/header.html'
          }
        },
        data: {
          // 路由缓存不存储
          doNotCache: false,

          // 是否需要登录校验
          requireLogin: true,

          // 是否需要鉴权
          requireAuthentication: true
        }
      })

      // 管理员
      .state('admin', {
        abstract: true,
        url: '/admin/:osId',
        views: {
          header: {
            templateUrl: 'app/views/commons/header.html'
          }
        },
        resolve: {
          isAdmin: ['Authentication', function (Authentication) {
            return Authentication.isAdmin()
          }]
        },
        data: {
          // 路由缓存不存储
          doNotCache: false,

          // 是否需要登录校验
          requireLogin: true,

          // 是否需要鉴权
          requireAuthentication: true
        }
      })

      // 超级管理员
      .state('super', {
        abstract: true,
        url: '/manager',
        views: {
          header: {
            templateUrl: 'app/views/commons/header.html'
          }
        },
        resolve: {
          isAdmin: ['Authentication', function (Authentication) {
            return Authentication.isAdmin(true)
          }]
        },
        data: {
          // 路由缓存不存储
          doNotCache: false,

          // 是否需要登录校验
          requireLogin: true,

          // 是否需要鉴权
          requireAuthentication: false
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
          // 路由缓存不存储
          doNotCache: true,

          // 是否需要登录校验
          requireLogin: false,

          // 是否需要鉴权
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
