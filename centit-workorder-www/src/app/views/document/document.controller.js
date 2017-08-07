(function() {
  'use strict'

  angular.module('workorder')
    .controller('DocumentController', DocumentController)

  /** @ngInject */
  function DocumentController($stateParams, DocAPI)
  {

      const vm = this;
      vm.submitRate = submitRate;
      vm.hoveringOver = hoveringOver;
      vm.flag = false;

      vm.setHTML = function(doc,showlevels){
        vm.html = doc.docFile;
        vm.htmlTitle = doc.docTitle;
        if(showlevels)
          doc[showlevels] = !doc[showlevels];

        //记录被评分的文章id
        vm.docId = doc.docId;
        //初始化为0
        vm.rate = 0;
        vm.flag=true;
      }

      activate();

      function activate(){
        queryDocLinks();

      }
      //查询文档的所有链接标签
      function queryDocLinks(){
       return vm.docLinks = DocAPI.levelSearch($stateParams)
      }

      //评分
      vm.rate = 0;

      function hoveringOver (value) {
        vm.overStar = value;
        if(vm.overStar<5)
          vm.showMsg='很差';
        else if(vm.overStar<=8)
          vm.showMsg = '一般';
        else
          vm.showMsg='很好';
      };

      function submitRate(){

        DocAPI.score(Object.assign({docId:vm.docId},$stateParams),vm.rate);
      }

  }
})();
