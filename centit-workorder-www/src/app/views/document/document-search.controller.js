(function () {
  'use strict'

  angular.module('workorder')
    .controller('DocumentSearchController', DocumentSearchController)

  /** @ngInject */
  function DocumentSearchController( $stateParams, $state,DocAPI) {
    let vm = this;
    vm.keyWord = $stateParams.keyWord;
    vm.isAdmin = $stateParams.isAdmin;

    active()

    function active() {
      vm.documents = DocAPI.fullSearch(Object.assign({}, $stateParams))

    }

    vm.search = function(){
      $state.go('root.document.search',{osId:$stateParams.osId,keyWord:vm.keyWord,isAdmin:vm.isAdmin})
    }

    vm.go = function (docId) {
      if(!docId)
        docId = 'doc2'
      if(vm.isAdmin){
        $state.go('root.document.view',{osId:$stateParams.osId,docId})
      }else{
        $state.go('admin.document.view',{osId:$stateParams.osId,docId})
      }
    }
    // // 因为在父路由中获取不到 docId，在这里传递过去
    // $scope.$emit('document.selected', $stateParams.docId)
  }
})();
