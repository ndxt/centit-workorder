(function() {
  'use strict';

  angular.module('workorder')
    .controller('HeadController', HeadController);

  /** @ngInject */
  function HeadController(OsAPI,$stateParams,Authentication) {
    const vm = this;

    Authentication.get()
      .then(user => vm.user = user)

    active()
    function active(){
      queryOs()
    }

    function queryOs() {
      vm.os = OsAPI.get($stateParams)
    }
  }
})();
