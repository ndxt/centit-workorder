(function() {
  'use strict'

  angular.module('workorder')
    .run(routerRun)

  /** @ngInject */
  function routerRun (routerHelper) {

    const states = [
      // 我的工单
      {
        state: 'admin.question',
        config: {
          url: '/questions',
          views: {
            'main@': {
              templateUrl: 'app/views/question/question.admin.html',
              controller: 'QuestionAdminController',
              controllerAs: 'vm'
            }
          },
          data: {
            title: '我的工单',
            bodyClass: 'question'
          }
        }
      },

      // 我的工单-详情
      {
        state: 'admin.question.view',
        config: {
          url: '/:questionId/view',
          views: {
            'main@': {
              templateUrl: 'app/views/question/question.admin-view.html',
              controller: 'QuestionAdminViewController',
              controllerAs: 'vm'
            }
          },
          data: {
            title: '工单详情',
            bodyClass: 'question-view'
          }
        }
      }
    ]

    routerHelper.addRouterStates(states)
  }
})()
