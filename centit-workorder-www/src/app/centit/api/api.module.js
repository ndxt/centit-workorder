;(function () {
  'use strict'

  angular.module('centit.api', [
    'ngResource',
    'centit.filter'
  ])
    .constant('ApiContextPath', '/workorder')
})();
