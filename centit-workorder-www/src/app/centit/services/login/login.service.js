(function () {
  'use strict'

  angular.module('centit.service')
    .factory('LoginService', LoginService)

  /** @ngInject */
  function LoginService ($q, LoginAPI) {
    const UserNotFoundRegExp = /^login error:user:(.*)not found/
    const UserBadCredentialsRegExp = /^login error:Bad credentials/

    return {
      login
    }

    function login (user, isSilent = true) {
      return LoginAPI
        .login({ _silent: isSilent }, user).$promise
        .then(function (data) {
          return data
        })
        .catch(function (rejection) {
          let statusText = rejection.statusText

          // 处理 用户名不存在、密码错误、用户锁定等异常
          if (UserNotFoundRegExp.test(statusText)) {
            return $q.reject('Error_Login_User_Not_Found')
          } else if (UserBadCredentialsRegExp.test(statusText)) {
            return $q.reject('Error_Login_Bad_Credentials')
          } else {
            $q.reject(rejection)
          }
        })
    }
  }
})();
