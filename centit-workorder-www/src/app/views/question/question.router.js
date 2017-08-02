(function() {
  'use strict'

  angular.module('workorder')
    .run(routerRun)

  /** @ngInject */
  function routerRun (routerHelper) {

    const states = [
      // 我的工单
      {
        state: 'root.question',
        config: {
          url: '/questions',
          views: {
            'main@': {
              templateUrl: 'app/views/question/question.html',
              controller: 'QuestionController',
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
        state: 'root.question.view',
        config: {
          url: '/:questionId',
          views: {
            'main@': {
              templateUrl: 'app/views/question/question-view.html',
              controller: 'QuestionViewController',
              controllerAs: 'vm'
            }
          },
          data: {
            title: '我的工单详情',
            bodyClass: 'question-view'
          }
        }
      }
    ]

    routerHelper.addRouterStates(states)
  }
})()
