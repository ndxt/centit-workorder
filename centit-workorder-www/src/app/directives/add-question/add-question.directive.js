(function() {
  'use strict'

  angular.module('workorder')
    .factory('QuestionModalService', QuestionModal)
    .directive('centitAddQuestion', Directive)

  /** @ngInject */
  function QuestionModal($stateParams,$uibModal,toastr,CatalogAPI,QuestionAPI
  ) {

    return {
      openModal
    }

    /**
     * 打开新增工单对话框
     * @param catalog
     * @param question
     */
    function openModal(catalog, question) {
      return $uibModal.open({
        templateUrl: 'app/views/question/question-modal.html',
        controller: 'QuestionModalController',
        controllerAs: 'vm',
        resolve: {
          catalog() {
            // 从问题中获取类型信息
            if (question && !catalog) {
              return questionResolve()
                .then(obj => catalog = obj.catalogId)
                .then(catalogResolve)
            }

            return catalogResolve()
          },
          question: questionResolve
        }
      }).result
        .then(function(obj) {
          // 新增
          if (!question || !question.questionId) {
            toastr.success(`工单【${obj.title}】创建成功`)
          }
          else {
            toastr.success(`工单【${obj.title}】修改成功`)
          }

          return obj
        })

      /**
       * 获取问题对象
       * @returns {*}
       */
      function questionResolve() {
        // 传入问题id
        if (angular.isString(question)) {
          return QuestionAPI.get(Object.assign({
            questionId: question
          }, $stateParams)).$promise
        }

        return Promise.resolve(question)
      }

      /**
       * 获取类型对象
       * @returns {*}
       */
      function catalogResolve() {
        // 传入类型id
        if (angular.isString(catalog)) {
          return CatalogAPI.get(Object.assign({
            catalogId: catalog
          }, $stateParams)).$promise
        }

        return Promise.resolve(catalog)
      }
    }


  }

  /** @ngInject */
  function Directive($state, QuestionModalService) {
    return {
      restrict: 'EA',
      scope: {
        catalog: '=',
        question: '='
      },
      link: function(scope, btn) {
        btn.on('click', function(e) {
          e.preventDefault()

          QuestionModalService.openModal(scope.catalog, scope.question)
            .then(obj => $state.go('root.question.view', { questionId: obj.questionId }))
        })
      }
    }
  }
})();
