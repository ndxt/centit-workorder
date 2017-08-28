(function() {
  'use strict'

  angular.module('workorder')
    .run(routerRun)

  /** @ngInject */
  function routerRun (routerHelper) {

    const states = [
      // 系统帮助
      {
        state: 'root.document',
        config: {
          url: '/documents',
          views: {
            'main@': {
              templateUrl: 'app/views/document/document.html',
              controller: 'DocumentController',
              controllerAs: 'vm'
            },
            'breadcrumb@':""
          },
          data: {
            title: '知识库',
            bodyClass: 'document'
          }
        }
      },

      // 系统帮助-详情
      {
        state: 'root.document.view',
        config: {
          url: '/:docId',
          views: {
            'doc': {
              templateUrl: 'app/views/document/document-view.html',
              controller: 'DocumentViewController',
              controllerAs: 'vm'
            }
          },
          data: {
            title: '知识库查看',
            bodyClass: 'document-view'
          }
        }
      },
      // 知识库检索
      {
        state: 'root.document.search',
        config: {
          url: '/key/:keyWord/:isAdmin',
          views: {
            'main@': {
              templateUrl: 'app/views/document/document-search.html',
              controller: 'DocumentSearchController',
              controllerAs: 'vm'
            }
          },
          data: {
            title: '知识库检索',
            bodyClass: 'document-view'
          }
        }
      }
    ]

    routerHelper.addRouterStates(states)
  }
})();
