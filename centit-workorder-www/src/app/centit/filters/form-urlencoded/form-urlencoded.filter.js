;(function () {
  'use strict'

  angular.module('centit.filter')
    .filter('formUrlencoded', formUrlEncoded)

  /** @ngInject */
  function formUrlEncoded () {
    return function (params) {
      return Object.assign({}, params, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },

        transformRequest: function (data) {
          const str = []
          for (let name in data) {
            if (!data.hasOwnProperty(name)) {
              continue
            }
            str.push(encodeURIComponent(name) + '=' + encodeURIComponent(data[name]))
          }
          return str.join('&')
        }
      })
    }
  }
})();
