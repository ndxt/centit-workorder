/* global malarkey:false */
(function() {
  'use strict';

  angular
    .module('workorder')
    .constant('config', {
      contextPath: '/api'
    })
    .constant('malarkey', malarkey)

})();
