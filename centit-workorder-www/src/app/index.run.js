(function() {
  'use strict';

  angular
    .module('workorder')
    .run(mockRun)

  /** @ngInject */
  function mockRun($httpBackend) {
    $httpBackend.whenGET(/views/).passThrough()
  }

})();
