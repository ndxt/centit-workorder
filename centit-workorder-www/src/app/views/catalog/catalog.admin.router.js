(function() {
  'use strict'

  angular.module('workorder')
    .run(routerRun)

  /** @ngInject */
  function routerRun (routerHelper) {

    const states = [
      // 首页-管理员
      {
        state: 'admin.catalog',
        config: {
          url: '/catalogs',
          views: {
            'main@': {
              templateUrl: 'app/views/dashboard/dashboard.admin.html',
              controller: 'DashboardAdminController',
              controllerAs: 'vm'
            }
          },
          data: {
            title: '管理问题类别',
            bodyClass: 'catalog-admin'
          }
        }
      }
    ]

    routerHelper.addRouterStates(states)
  }
})();
