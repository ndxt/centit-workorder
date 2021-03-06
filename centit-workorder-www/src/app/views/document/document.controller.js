(function () {
  'use strict'

  angular.module('workorder')
    .controller('DocumentController', DocumentController)

  /** @ngInject */
  function DocumentController($timeout, $scope, $state, $stateParams, DocAPI) {

    const vm = this;

    vm.treeInstance = {}
    vm.docLinks = []
    vm.currentDocId = null
    vm.onSelectNode = onSelectNode

    vm.search = function(){
      $state.go('root.document.search',{osId:$stateParams.osId,keyWord:vm.keyWord,isAdmin:'F'})
    }

    vm.enterEvent = function (e) {
      var key = e.which;
      if(key ==13)
        vm.search();
    }

    activate();


    function activate() {
      queryDocLinks();

      // 监听从子路由发送来的文档id事件
      $scope.$on('document.selected', function(e, docId) {
        if (docId !== vm.currentDocId) {
          vm.currentDocId = docId
        }
      })
    }

    /**
     * 当手动选择树型节点时
     * @param branch
     */
    function onSelectNode(branch) {
      if (!branch) return
      if (branch.children && branch.children.length) return
      vm.currentDocId = branch.docId
      $state.go('root.document.view', {
        docId: branch.docId
      })
    }

    /**
     * 查询文档树
     */
    function queryDocLinks() {
      return DocAPI.levelSearch($stateParams).$promise
        .then(function(res) {
          vm.docLinks = res

          let node = getFirstChild(vm.docLinks, vm.currentDocId)
          $timeout(() => vm.treeInstance.select_branch(node))
        })
    }

    /**
     * 从文档树中获取第一个指定的节点
     * 如果没有指定docId，那么会返回第一个叶子节点
     * @param list 文档树数据
     * @param docId 指定docId
     * @returns {*}
     */
    function getFirstChild(list, docId) {
      let length = list.length
      let result
      for (let i = 0; i < length; i++) {
        let node = list[i]
        let children = node.children
        if (children && children.length) {
          result = getFirstChild(children, docId)
          if (result) {
            return result
          }
        }

        if (!docId || node.docId === docId) {
          return node
        }
      }
    }

  }
})();
