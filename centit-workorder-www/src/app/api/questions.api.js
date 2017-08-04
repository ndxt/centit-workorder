(function() {
  'use strict'

  angular.module('workorder')
    .factory('QuestionAPI', QuestionAPI)

  /** @ngInject */
  function QuestionAPI($resource, config) {

    const baseUrl = `${config.contextPath}/service/os/:osId/questions`;

    const queryTop = {
      url: `${baseUrl}/:count/top?catalog=:catalogId`,
      method: 'GET',
      isArray: true
    }
    const update = {
      method: 'PUT'
    }

    return $resource(`${baseUrl}/:questionId`, null, {
      queryTop,update
    })
  }
})();

