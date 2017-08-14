(function() {
  'use strict';

  angular.module('workorder')
    .controller('DashboardAdminController', DashboardController);

  /** @ngInject */
  function DashboardController(
    $stateParams,
    CatalogAPI,isAdmin
  ) {
    const vm = this;
    const osId = $stateParams.osId;

    vm.osId = osId
    vm.create = create
    vm.edit = edit
    vm.remove = remove
    vm.queryCatalogs = queryCatalogs
    vm.isAdmin = isAdmin

    activate()

    ////////////////////////////////////////////////////////////////

    function activate() {
      queryCatalogs()
    }

    function create() {

    }

    function remove(catalog) {
      CatalogAPI.delete({osId:$stateParams.osId,catalogId:catalog.catalogId})
        .$promise
        .then(function(){
          queryCatalogs()
        });

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
