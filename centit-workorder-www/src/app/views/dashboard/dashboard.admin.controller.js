(function() {
  'use strict';

  angular.module('workorder')
    .controller('DashboardAdminController', DashboardController);

  /** @ngInject */
  function DashboardController(
    $stateParams,ConfirmModalService,
    CatalogAPI,isAdmin
  ) {
    const vm = this;
    const osId = $stateParams.osId;

    vm.osId = osId
    vm.create = create
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
      ConfirmModalService.openModal('确定删除该类别吗？')
        .then(function () {
          CatalogAPI.delete({osId:$stateParams.osId,catalogId:catalog.catalogId})
            .$promise
            .then(function(){
              queryCatalogs()
            });
        })
    }

    function queryCatalogs() {
      vm.catalogs = CatalogAPI.rankList({
        osId
      })
    }
  }
})();
