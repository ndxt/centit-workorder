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
    const allQuestionInfo = {
      url: `${baseUrl}/questionInfo/:questionId`,
      method: 'GET'
    }
    //给工单打分
    const comment = {
      url: `${baseUrl}/:questionId/comment?evaluateScore=:evaluateScore`,
      method: 'PUT'
    }
    //添加或修改主负责人
    const updateOperator = {
      url: `${baseUrl}/:questionId/operator`,
      method: 'PUT'
    }
    //添加或修改协助负责人
    const assistOperator = {
      url: `${baseUrl}/assistOperator`,
      method: 'POST',
      isArray: true
    }
    //获取协助负责人
    const listAssistOperator = {
      url: `${baseUrl}/assistOperator/:questionId`,
      method: 'GET',
      isArray: true
    }
    //分配给自己
    const grab = {
      url: `${baseUrl}/:questionId/grab`,
      method: 'PUT'
    }
    const update = {
      method: 'PUT'
    }

    return $resource(`${baseUrl}/:questionId`, null, {
      queryTop,allQuestionInfo,update,comment,updateOperator,assistOperator,grab,listAssistOperator
    })
  }
})();

