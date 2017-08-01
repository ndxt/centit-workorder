(function() {
  'use strict'

  angular.module('workorder')
    .controller('LoginController', LoginController)

  /**  @ngInject */
  function LoginController($state, MockLoginAPI, OsAPI, Authentication, Constant) {
    const vm = this

    vm.user = {
      userName: Constant.UserNameUser,
      osId: 'FILE_SVR'
    }

    vm.login = login
    activate()

    function activate() {
      // 清空用户缓存
      Authentication.clear()

      queryOs()
    }


    /**
     * 模拟登录请求
     * @param user
     * @returns {boolean}
     */
    function login(user) {
      return MockLoginAPI.login(user)

        // 保存用户信息
        .then(user => Authentication.save(user))

        // 跳转页面
        .then(function(user) {
          const userType = user.userType

          switch(userType) {
            // 管理员
            case Constant.UserTypeAdmin:
              $state.go(Constant.RouteAdmin, user)
              break

            // 普通用户
            case Constant.UserTypeUser:
              $state.go(Constant.RouteUser, user)
              break

            // 默认进普通用户页面
            default:
              $state.go(Constant.RouteUser, user)
          }
        })
    }

    /**
     * 查询系统信息
     */
    function queryOs() {
      vm.os = OsAPI.query()
    }
  }
})()
