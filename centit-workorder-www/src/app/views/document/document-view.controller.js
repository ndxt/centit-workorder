
(function() {
  'use strict'

  angular.module('workorder')
    .controller('DocumentViewController', DocumentViewController)

  /** @ngInject */
  function DocumentViewController($stateParams,DocAPI) {
    var vm = this;


    function active(){
      DocAPI.get(Object.assign({},$stateParams))
        .$promise
        .then(res=>vm.document = res)
    }
    active();

  }
})();
