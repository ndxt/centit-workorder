(function() {
  'use strict';

  angular.module('workorder')
    .controller('DashboardAdminController', DashboardController);

  /** @ngInject */
  function DashboardController(
    $stateParams,
    CatalogAPI
  ) {
    const vm = this;
    const osId = $stateParams.osId;

    activate();

    function activate() {
      queryCatalogs()
    }

    function queryCatalogs() {
      vm.catalogs = CatalogAPI.query({
        osId
      })

      console.log(vm.catalogs)
    }
  }
})();
