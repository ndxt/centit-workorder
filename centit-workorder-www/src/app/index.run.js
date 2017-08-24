(function() {
  'use strict';

  angular
    .module('workorder')
    .run(runBlock)

  /** @ngInject */
  function runBlock($window, toastr) {
    $window.$toastr = toastr
  }
})();
