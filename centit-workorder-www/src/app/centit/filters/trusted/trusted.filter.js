(function () {
  'use strict'

  angular.module('centit.filter')
    .filter('trustedHtml', trustedHtml)

  /** @ngInject */
  function trustedHtml ($sce) {
    return function(html){
      return $sce.trustAsHtml(html)
    }
  }
})();
