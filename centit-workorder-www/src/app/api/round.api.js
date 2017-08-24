(function () {
  'use strict'

  angular.module('workorder')
    .factory('RoundAPI', RoundAPI)

  /** @ngInject */
  function RoundAPI($resource, config) {
    const baseUrl = `${config.contextPath}/service/questions/:questionId/round`

    return $resource(`${baseUrl}/:roundId`, null, {
      //追问
      supplemental:{
        url:`${baseUrl}/supplemental`,
        method:'POST'
      },

      //回复
      reply:{
        url:`${baseUrl}/reply`,
        method:'POST'
      },
      //显示
      listT:{
        url:`${baseUrl}/questionRoundShowUser`,
        method:'GET',
        isArray: true
      },
      //
      changeUserTag:{
        url:`${baseUrl}/:roundId/showUserTag`,
        method:'PUT'
      }
    })
  }
})();
