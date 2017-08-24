(function() {
  'use strict';

  angular.module('workorder')
    .controller('BreadcrumbController', BreadcrumbController);

  /** @ngInject */
  function BreadcrumbController(
    $rootScope,
    $state,
    routerHelper
  ) {
    const vm = this;

    vm.directory = getParents($state.current.name, $state.params)

    vm.go = function(state) {
      $state.go(state.name, state.params)
    }

    function getParents(name, params) {
      const parents = routerHelper.getParentState(name)
      parents.push(name)
      parents.shift()
      return parents.map(function(name){
        let state = $state.get(name)
        return {
          name: state.name,
          title: state.data.title,
          params: params
        };
      })
    }

   $rootScope.$on('changeRoute', function(e, state, params) {
      vm.directory = getParents(state.name, params);
    })
  }
})();
