(function () {
  'use strict'

  angular.module('workorder')
    .factory('RoundAPI', RoundAPI)

  /** @ngInject */
  function RoundAPI($resource, config) {
    const baseUrl = `${config.contextPath}/questions/:questionId/round`

    return $resource(`${baseUrl}/:roundId`, null, {})
  }
})();
