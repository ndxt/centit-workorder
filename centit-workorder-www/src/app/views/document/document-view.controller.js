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

      // 因为在父路由中获取不到 docId，在这里传递过去
      $scope.$emit('document.selected', $stateParams.docId)
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
