;(function () {
  'use strict'

  angular.module('centit.api')
    .factory('LoginAPI', LoginAPI)

  /** @ngInject */
  function LoginAPI ($resource, $filter, ApiContextPath) {

    return $resource('', null, {
      login: login(),
      logout: logout(),
      hasLogin: hasLogin()
    })

    function hasLogin() {
      return {
        method: 'GET',
        url: ApiContextPath + '/system/mainframe/hasLogin'
      }
    }

    /**
     * 登录
     * @returns {*}
     */
    function login () {
      let api = {
        method: 'POST',
        url: ApiContextPath + '/login',
        params: {
          ajax: true
        }
      }

      return $filter('formUrlencoded')(api)
    }

    /**
     * 登出
     * @returns {{method: string, url: string}}
     */
    function logout () {
      return {
        method: 'GET',
        url: ApiContextPath + '/logout'
      }
    }
  }
})()
