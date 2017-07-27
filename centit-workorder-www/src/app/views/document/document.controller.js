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

      vm.setHTML = function(html){
        vm.html = html;

      }

      vm.submitRate = function(){
        var score = $('#function-demo').raty('score');
        alert(score);
      }

      activate();

      function activate(){
        queryDocLinks();

      }
      //查询文档的所有链接标签
      function queryDocLinks(){
       return DocAPI.query(Object.assign({
         }, $stateParams))
         .$promise
         .then(res => vm.docLinks = res);
      }

  }
})();
