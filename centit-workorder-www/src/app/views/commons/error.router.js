(function() {
  'use strict'

  angular.module('workorder')
    .run(routerRun)

  /** @ngInject */
  function routerRun (routerHelper) {

    const states = [
      {
        state: 'blank.401',
        config: {
          url: '/401',
          views: {
            'main@': {
              templateUrl: 'app/views/commons/401.html',
              controller: 'ErrorPageController',
              controllerAs: 'vm'
            }
          },
          data: {
            // 切换路由时，如果想改变title显示，可以设置此属性
            title: '401',
            bodyClass: 'error-401'
          }
        }
      },

      {
        state: 'blank.404',
        config: {
          url: '/404',
          views: {
            'main@': {
              templateUrl: 'app/views/commons/404.html',
              controller: 'ErrorPageController',
              controllerAs: 'vm'
            }
          },
          data: {
            // 切换路由时，如果想改变title显示，可以设置此属性
            title: '404',
            bodyClass: 'error-404'
          }
        }
      },

      {
        state: 'blank.500',
        config: {
          url: '/500',
          views: {
            'main@': {
              templateUrl: 'app/views/commons/500.html',
              controller: 'ErrorPageController',
              controllerAs: 'vm'
            }
          },
          data: {
            // 切换路由时，如果想改变title显示，可以设置此属性
            title: '500',
            bodyClass: 'error-500'
          }
        }
      }
    ]

    routerHelper.addRouterStates(states)
  }
})();
