(function() {
  'use strict';

  angular
    .module('workorder')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider

      // 默认布局
      .state('root', {
        abstract: true,
        url: '/os/:osId',
        views: {
          header: {
            templateUrl: 'app/views/commons/header.html'
          }
        }
      })

      .state('blank', {
        abstract: true,
        url: '',
        views: {
          header: ''
        }
      })

      /** 首页 **/
      .state('root.catalog', {
        url: '/catalogs',
        views: {
          'main@': {
            templateUrl: 'app/views/dashboard/dashboard.html',
            controller: 'DashboardController',
            controllerAs: 'vm'
          }
        }
      })

      // 首页-分类详情
      .state('root.catalog.view', {
        url: '/:catalogId',
        views: {
          'main@': {
            templateUrl: 'app/views/catalog/catalog.html',
            controller: 'CatalogController',
            controllerAs: 'vm'
          }
        }
      })

      /** 我的工单 **/
      .state('root.question', {
        url: '/question',
        views: {
          'main@': {
            templateUrl: 'app/views/question/question.html',
            controller: 'QuestionController',
            controllerAs: 'vm'
          }
        }
      })

      // 我的工单-详情
      .state('root.question.view', {
        url: '/:questionId',
        views: {
          'main@': {
            templateUrl: 'app/views/question/question-view.html',
            controller: 'QuestionViewController',
            controllerAs: 'vm'
          }
        }
      })

      /** 系统帮助 **/
      .state('root.document', {
        url: '/document',
        views: {
          'main@': {
            templateUrl: 'app/views/document/document.html',
            controller: 'DocumentController',
            controllerAs: 'vm'
          }
        }
      })

      // 系统帮助-详情
      .state('root.document.view', {
        url: '/:documentId',
        views: {
          'main@': {
            templateUrl: 'app/views/document/document-view.html',
            controller: 'DocumentViewController',
            controllerAs: 'vm'
          }
        }
      })
      //系统客服
      .state('root.service',{
        url:'/service',
        views:{
          'main@':{
            templateUrl: 'app/views/service/service.html',
            controller:'ServiceViewController',
            controllerAs:'vm'
          }
        }
      })

    $urlRouterProvider.otherwise('/login')
  }

})();
