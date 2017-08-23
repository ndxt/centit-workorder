(function() {
  'use strict';

  angular.module('workorder')
    .controller('HeadController', HeadController);

  /** @ngInject */
  function HeadController(OsAPI,$stateParams) {
    const vm = this;

    active()
    function active(){
      queryOs()
    }

    function queryOs() {
      vm.os = OsAPI.get($stateParams)
    }
  }
})();
