(function(){
  'user strict'

  angular.module('workorder')
    .factory('ConfirmModalService',ConfirmModalService)

  function ConfirmModalService($uibModal){
      return {
        openModal : openModal
      };

      function openModal(title){
        return $uibModal.open({
          templateUrl:'app/views/confirm/confirm-modal.html',
          controller:'ConfirmModalController',
          controllerAs:'vm',
          size:'sm',
          resolve:{
            title:function () {
              return title;
            }
          }
        }).result
          .then(function (res) {
            if (res) return res;
            throw res
          })
      }
  }

})();
