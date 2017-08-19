(function() {
  'use strict'

  angular.module('workorder')
    .directive('woModalCatalog', Directive)

  /** @ngInject */
  function Directive($log, CatalogModalService) {
    return {
      restrict: 'EA',
      scope: {
        os: '=',
        catalog: '=',
        parentId: '=',
        onSuccess: '&',
        onError: '&',
        freshData:'='
      },

      link: function(scope, btn) {
        btn.off('click.wo-modal-catalog')
          .on('click.wo-modal-catalog', function(e) {
            e.preventDefault()

            CatalogModalService.openModal(scope.os, scope.catalog,scope.parentId,scope.freshData)
              .then($log.info)
              .catch($log.error)
        })
      }
    }
  }
})();
