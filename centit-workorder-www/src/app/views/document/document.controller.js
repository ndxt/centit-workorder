(function() {
  'use strict'

  angular.module('workorder')
    .controller('DocumentController', DocumentController)

  /** @ngInject */
  function DocumentController(
    $stateParams,
    $log,
    DocAPI
  ) {

      const vm = this;

      vm.hide = true;
      vm.test = function(){
        alert('More handsome~');
      }

      vm.toggle = function(){
        vm.hide = vm.hide == 'true'?'false':true;
      }
      vm.docLinks = [];
      vm.html = '';

      vm.setHTML = function(doc,showlevels){
        vm.html = doc.docFile;
        if(showlevels)
        doc[showlevels] = !doc[showlevels];

      }

      activate();

      function activate(){
        queryDocLinks();

      }
      //查询文档的所有链接标签
      function queryDocLinks(){
       return vm.docLinks = DocAPI.levelSearch($stateParams)
      }

  }
})();
