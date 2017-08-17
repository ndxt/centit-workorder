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

      // 我的工单-编辑
      {
        state: 'root.question.add',
        config: {
          url: '/new/:catalogId',
          views: {
            'main@': {
              templateUrl: 'app/views/question/question-edit.html',
              controller: 'QuestionEditController',
              controllerAs: 'vm'
            }
          },
          resolve: {
            question: ['$stateParams',function($stateParams) {
              return {catalogId:$stateParams.catalogId}
            }]
          },
          data: {
            title: '新增工单',
            bodyClass: 'question-edit'
          }
        }
      },

      // 我的工单-编辑
      {
        state: 'root.question.edit',
        config: {
          url: '/:questionId/edit',
          views: {
            'main@': {
              templateUrl: 'app/views/question/question-edit.html',
              controller: 'QuestionEditController',
              controllerAs: 'vm'
            }
          },
          resolve: {
            question: ['$stateParams', 'QuestionAPI', function($stateParams, QuestionAPI) {
              const questionId = $stateParams.questionId
              const osId = $stateParams.osId
              return QuestionAPI.get({
                osId,
                questionId
              })
            }]
          },
          data: {
            title: '编辑工单',
            bodyClass: 'question-edit'
          }
        }
      },

      // 我的工单-详情
      {
        state: 'root.question.view',
        config: {
          url: '/:questionId/view',
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
})();
