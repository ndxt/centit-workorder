(function() {
  'use strict'

  angular.module('workorder')
    .factory('QuestionAPI', QuestionAPI)

  /** @ngInject */
  function QuestionAPI($resource, config) {

    const baseUrl = `${config.contextPath}/os/:osId/questions`;

    const queryTop = {
      url: `${baseUrl}/:count/top?catalog=:catalogId`,
      method: 'GET',
      isArray: true
    }

    return $resource(`${baseUrl}/:questionId`, null, {
      queryTop
    })
  }
})();

