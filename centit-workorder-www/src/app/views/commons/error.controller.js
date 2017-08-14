(function() {
  'use strict'

  angular.module('workorder')
    .controller('ErrorPageController', ErrorPageController)

  /** @ngInject */
  function ErrorPageController($state) {
    const vm = this

    if ($state.prev) {
      vm.prev = $state.prev
    }

    vm.back = back

    function back(prev) {
      if (prev && prev.name) {
        $state.go(prev.name, prev.params)
      }
    }
  }
})();
