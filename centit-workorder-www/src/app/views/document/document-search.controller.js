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
      if(vm.keyWord)
        vm.documents = DocAPI.fullSearch(Object.assign($stateParams,{keyWord:vm.keyWord}))
      else if(vm.isAdmin=='T')
        $state.go('admin.document',{osId:$stateParams.osId})
      else
        $state.go('root.document',{osId:$stateParams.osId})
    }

    vm.enterEvent = function (e) {
      var key = e.which;
      if(key ==13)
        vm.search();
    }

    vm.go = function (docId) {
      if(vm.isAdmin=='T'){
        $state.go('admin.document.view',{osId:$stateParams.osId,docId})
      }else{
        $state.go('root.document.view',{osId:$stateParams.osId,docId})
      }
    }

  }
})();
