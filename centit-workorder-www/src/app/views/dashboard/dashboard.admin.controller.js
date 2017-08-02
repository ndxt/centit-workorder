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

    vm.osId = osId
    vm.create = create
    vm.edit = edit
    vm.remove = remove

    activate()

    ////////////////////////////////////////////////////////////////

    function activate() {
      queryCatalogs()
    }

    function create() {

    }

    function remove(catalog) {

    }

    function edit(catalog) {

    }

    function queryCatalogs() {
      vm.catalogs = CatalogAPI.query({
        osId
      })
    }
  }
})();
