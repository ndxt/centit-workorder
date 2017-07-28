(function() {
  'use strict';

  angular
    .module('workorder')
    .run(runBlock)
    // .run(mockRun)

  /** @ngInject */
  function runBlock(toastr) {
    window.$toastr = toastr
  }

  /** @ngInject */
  function mockRun($httpBackend) {
    $httpBackend.whenGET(/views/).passThrough()
  }

})();
