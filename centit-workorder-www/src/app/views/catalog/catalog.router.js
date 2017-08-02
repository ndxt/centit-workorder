(function() {
  'use strict'

  angular.module('workorder')
    .run(routerRun)

  /** @ngInject */
  function routerRun (routerHelper) {

    const states = [
      // 首页
      {
        state: 'root.catalog',
        config: {
          url: '/catalogs',
          views: {
            'main@': {
              templateUrl: 'app/views/dashboard/dashboard.html',
              controller: 'DashboardController',
              controllerAs: 'vm'
            }
          },
          data: {
            title: '问题类别',
            bodyClass: 'catalog'
          }
        }
      },

      // 首页-分类详情
      {
        state: 'root.catalog.view',
        config: {
          url: '/:catalogId',
          views: {
            'main@': {
              templateUrl: 'app/views/catalog/catalog.html',
              controller: 'CatalogController',
              controllerAs: 'vm'
            }
          },
          data: {
            title: '问题类别详情',
            bodyClass: 'catalog-view'
          }
        }
      }
    ]

    routerHelper.addRouterStates(states)
  }
})()
