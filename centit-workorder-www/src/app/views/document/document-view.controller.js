(function () {
  'use strict'

  angular.module('workorder')
    .controller('DocumentViewController', DocumentViewController)

  /** @ngInject */
  function DocumentViewController($scope, $stateParams, DocAPI) {
    let vm = this
    vm.save = save
    vm.cancelEditing = cancelEditing

    active()

    function active() {
      vm.document = DocAPI.get(Object.assign({}, $stateParams))
    }

    function cancelEditing() {
      vm.editing = false
    }

    function save(content) {
      return DocAPI.editContent($stateParams, {
        content
      }).$promise.then(cancelEditing)
    }
  }
})();
