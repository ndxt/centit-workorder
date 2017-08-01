(function() {
  'use strict'

  angular.module('workorder')
    .run(routerRun)

  /** @ngInject */
  function routerRun (routerHelper) {

    const states = [
      {
        state: 'blank.login',
        config: {
          url: '/login',
          views: {
            'main@': {
              templateUrl: 'app/views/login/login.html',
              controller: 'LoginController',
              controllerAs: 'vm'
            }
          },
          data: {
            // 是否需要登录校验
            requireLogin: false,

            // 切换路由时，如果想改变title显示，可以设置此属性
            title: '模拟登录',

            // 在body上为路由添加特殊class
            bodyClass: 'login',

            // container默认布局为row，通过这个属性设置为其他值，如：column
            layout: undefined
          }
        }
      }
    ]

    routerHelper.addRouterStates(states)
  }
})()
