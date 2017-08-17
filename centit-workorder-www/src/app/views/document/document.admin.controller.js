(function () {
  'use strict'

  angular.module('workorder')
    .controller('DocumentAdminController', DocumentAdminController)

  /** @ngInject */
  function DocumentAdminController($timeout, $state, $stateParams, DocAPI) {

    const vm = this;

    vm.treeInstance = {}
    vm.docLinks = []
    vm.selectNode = selectNode

    activate();

    function activate() {
      queryDocLinks();
    }

    function selectNode(branch) {
      console.log(branch)
      if (branch.children && branch.children.length) return;

      $state.go('admin.document.view', {
        docId: branch.docId
      })
    }

    //查询文档的所有链接标签
    function queryDocLinks() {
      return DocAPI.levelSearch($stateParams).$promise
        .then(function (data) {
          vm.docLinks = data
          $timeout(function () {
            let branch = getFirstChild(data)
            vm.treeInstance.select_branch(branch)
          })
          console.log(vm.treeInstance)
        })
    }

    function getFirstChild(list) {
      let length = list.length
      for (let i = 0; i < length; i++) {
        let node = list[i]
        let children = node.children
        if (children && children.length) {
          return getFirstChild(children)
        }

        return node
      }
    }

  }
})();
