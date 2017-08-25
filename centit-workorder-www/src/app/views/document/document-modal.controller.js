(function () {
  'use strict'

  angular.module('workorder')
    .controller('DocumentModalController', DocumentModalController)

  /** @ngInject */
  function DocumentModalController($uibModalInstance, $stateParams, DocAPI,node,type) {
    let vm = this

    vm.cancel = function () {
      $uibModalInstance.dismiss();
    }
    vm.ok = function () {
      if(type=='add'){
        DocAPI.save({osId:$stateParams.osId}, vm.document)
          .$promise
          .then((val)=>{
            console.log(val)
            $uibModalInstance.close(val)
          })
      }else{
        DocAPI.update({osId:$stateParams.osId,docId:vm.document.docId}, vm.document)
          .$promise
          .then((val)=>{$uibModalInstance.close(val)})
      }
    }

    active()

    function active() {
      // vm.documents = DocAPI.query({osId: $stateParams.osId})
      if(type=='edit' && node) {
        vm.document = DocAPI.get({osId: $stateParams.osId, docId:node.docId})
      }else if(type=='add' && node){
        vm.document = {
          docLevel:node.docLevel+1,
          docPath:node.docId
        }
      }else if(type=='add' && !node){
        vm.document = {
          docLevel:1,
          docPath:0
        }
      }
    }
  }
})();
