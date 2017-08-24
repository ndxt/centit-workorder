(function() {
  'use strict';

  angular.module('workorder')
    .controller('DashboardController', DashboardController);

  /** @ngInject */
  function DashboardController(
    $stateParams,
    CatalogAPI,
    DocAPI
  ) {
    const vm = this;
    const osId = $stateParams.osId;

    activate();

    vm.ask = function (catalogId) {
      DocAPI.ask({catalogId})
    }

    function activate() {
      queryCatalogs()
    }

    function queryCatalogs() {
      vm.catalogs = CatalogAPI.rankList({
        osId
      })
    }
  }
})();
