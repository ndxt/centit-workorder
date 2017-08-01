(function() {
  'use strict'

  angular.module('workorder')
    .controller('LoginController', LoginController)

  /**  @ngInject */
  function LoginController($state, OsAPI, Constant) {
    const vm = this


    vm.user = {
      userName: Constant.UserNameUser,
      osId: 'FILE_SVR'
    }

    vm.login = login
    vm.os = queryOs()


    /**
     * 登录
     * @param user
     * @returns {boolean}
     */
    function login(user) {
      const userName = user.userName

      // 没有输入用户名
      if (!userName) {
        return false
      }

      if (userName === Constant.UserNameAdmin) {
        // 管理员登录
        user.userType = Constant.UserTypeAdmin
        $state.go(Constant.RouteUser, {
          osId: user.osId
        })
      } else {
        // 用户登录
        user.userType = Constant.UserNameUser
        $state.go(Constant.RouteUser, {
          osId: user.osId
        })
      }
    }

    /**
     * 查询系统信息
     */
    function queryOs() {
      return OsAPI.query()
    }
  }
})()
