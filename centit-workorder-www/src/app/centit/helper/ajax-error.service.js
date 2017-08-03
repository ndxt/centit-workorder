/* global $mdToast:false */
;(function () {
  'use strict'

  angular.module('centit.helper')
    .factory('ajaxErrorHelper', ajaxErrorHelper)

  /** @ngInject */
  function ajaxErrorHelper ($log) {

    let handlingAjaxError = false

    return {
      showError
    }

    function showError (rejection) {
      let config = rejection.config
      let isSilent = config.params ? !!config.params._silent : false
      let url = config.url
      let status = rejection.status
      let statusText = rejection.statusText

      if (!handlingAjaxError && !isSilent) {
        handlingAjaxError = true
        $log.error(`链接：${url} 发生错误，状态码：${status}，描述：${statusText}`)
        handlingAjaxError = false
      }
    }
  }
})()
