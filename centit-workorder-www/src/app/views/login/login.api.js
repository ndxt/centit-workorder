(function() {
  'use strict'

  angular.module('workorder')
    .factory('MockLoginAPI', LoginAPI)

  /** @ngInject */
  function LoginAPI($q, Constant) {

    return {
      login
    }

    function login(user) {
      let userName

      // 没有输入用户名
      if (!user || !(userName = user.userName)) {
        return $q.reject('用户名不能为空')
      }

      // 根据用户名设定用户类型（这里只是模拟，以后需要根据后台请求获取用户类型等信息）
      user.userType = userName === Constant.UserNameAdmin ? Constant.UserTypeAdmin : Constant.UserTypeUser

      return $q.resolve(user)
    }
  }
})()

