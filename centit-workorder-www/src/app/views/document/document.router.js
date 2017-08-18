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
            'breadcrumb@': ''
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
            title: '系统帮助详情',
            bodyClass: 'document-view'
          }
        }
      }
    ]

    routerHelper.addRouterStates(states)
  }
})();
