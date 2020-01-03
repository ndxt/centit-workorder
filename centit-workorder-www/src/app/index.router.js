(function() {
  'use strict';

  angular
    .module('workorder')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider

      .state('login', {
        url: '/login',
        views: {
          header: {
            templateUrl: 'app/views/login/login.html',
            controller: 'LoginController as vm'
          }
        }
      })

      // 默认布局
      .state('root', {
        abstract: true,
        url: '/user/:osId',
        views: {
          header: {
            templateUrl: 'app/views/commons/header.html',
            controller: 'HeadController as vm'
          },
          breadcrumb: {
            templateUrl: 'app/views/directory/breadcrumbs.html',
            controller: 'BreadcrumbController as vm'
          }
        },
        data: {
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
            templateUrl: 'app/views/commons/header.html',
            controller: 'HeadController as vm'
          },
          breadcrumb: {
            templateUrl: 'app/views/directory/breadcrumbs.html',
            controller: 'BreadcrumbController as vm'
          }
        },
        // resolve: {
        //   isAdmin: ['Authentication', function (Authentication) {
        //     return Authentication.isAdmin()
        //   }]
        // },
        data: {
          // 是否需要登录校验
          requireLogin: false,

          // 是否需要鉴权
          requireAuthentication: false
        }
      })

      // 超级管理员
      .state('super', {
        abstract: true,
        url: '/manager',
        views: {
          header: {
            templateUrl: 'app/views/commons/header.html',
            controller: 'HeadController as vm'
          },
          breadcrumb: {
            templateUrl: 'app/views/directory/breadcrumbs.html',
            controller: 'BreadcrumbController as vm'
          }
        },
        resolve: {
          isAdmin: ['Authentication', function (Authentication) {
            return Authentication.isAdmin(true)
          }]
        },
        data: {
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
          doNotCache: true
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
