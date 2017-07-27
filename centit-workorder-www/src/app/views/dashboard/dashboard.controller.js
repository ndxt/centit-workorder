(function() {
  'use strict';

  angular.module('workorder')
    .controller('DashboardController', DashboardController);

  /** @ngInject */
  function DashboardController(
    $timeout,
    $stateParams,
    QuestionAPI,
    CatalogAPI
  ) {
    const vm = this;

    vm.isNavCollapsed = true;
    vm.classAnimation = 'rubberBand';
    vm.questions = [];

    activate();

    function activate() {
      animate()
      queryTopQuestions(10)

      queryCatalogs(10)
    }

    function queryTopQuestions(count) {
      return QuestionAPI.queryTop(Object.assign({
        count: count
      }, $stateParams))
        .$promise
        .then(res => vm.questions = res)
    }

    function queryCatalogs(count) {
      return CatalogAPI.query(Object.assign({
        count: count
      }, $stateParams))
        .$promise
        .then(res => vm.catalogs = res)
    }

    function animate() {
      $timeout(function() {
        vm.classAnimation = vm.classAnimation === 'rubberBand' ? '' : 'rubberBand';
        animate();
      }, 5000);
    }
  }
})();
