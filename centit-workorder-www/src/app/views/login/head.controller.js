(function() {
  'use strict';

  angular.module('workorder')
    .controller('HeadController', HeadController);

  /** @ngInject */
  function HeadController(OsAPI,$stateParams,Authentication,LoginAPI,$state) {
    const vm = this;
    vm.logout = logout

    function logout() {
      LoginAPI.logout().$promise.then(user=>$state.go('login'));
    }

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
