(function () {
  'use strict'

  angular.module('centit.api')
    .factory('LoginAPI', LoginAPI)

  /** @ngInject */
  function LoginAPI ($resource, $filter, ApiContextPath) {

    return $resource('', null, {
      login: login(),
      logout: logout(),
      hasLogin: hasLogin(),
      currentuser:currentuser()
    })

    function hasLogin() {
      return {
        method: 'GET',
        url: ApiContextPath + '/system/mainframe/hasLogin'
      }
    }
    function currentuser() {
      return {
        method: 'GET',
        url: ApiContextPath + '/system/mainframe/currentuser'
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
        url: ApiContextPath + '/system/mainframe/logout'
      }
    }
  }
})();
