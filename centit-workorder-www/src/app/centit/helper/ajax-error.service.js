(function () {
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
      let method = config.method
      let status = rejection.status
      let statusText = rejection.statusText

      if (!handlingAjaxError && !isSilent) {
        handlingAjaxError = true
        $log.error(`链接：${url} 发生错误\n状态码：${status}\n方法：${method}\n描述：${statusText}`)
        handlingAjaxError = false
      }
    }
  }
})();
