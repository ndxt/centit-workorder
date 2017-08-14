(function () {
  'use strict'

  angular.module('workorder')
    .factory('RoundAPI', RoundAPI)

  /** @ngInject */
  function RoundAPI($resource, config) {
    const baseUrl = `${config.contextPath}/service/questions/:questionId/round`

    return $resource(`${baseUrl}/:roundId`, null, {
      supplemental:{
        url:`${baseUrl}/supplemental`,
        method:'POST'
      },
      reply:{
        url:`${baseUrl}/reply`,
        method:'POST'
      },
      listT:{
        url:'${baseUrl}/questionRoundShowUser',
        mrthod:'GET'
      }
    })
  }
})();
