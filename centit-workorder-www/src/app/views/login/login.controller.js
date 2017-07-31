(function() {
  'use strict'

  angular.module('workorder')
    .controller('LoginController', LoginController)

  /**  @ngInject */
  function LoginController($state) {
    const vm = this
    const UserNameAdmin = 'admin'
    const UserNameUser = 'user'
    const RouteUser = 'root.catalog'

    vm.user = {
      userName: UserNameUser,
      osId: 'ycxmglpt'
    }

    vm.login = login


    function login(user) {
      const userName = user.userName

      // 没有输入用户名
      if (!userName) {
        return false
      }

      // 模拟用户或者管理员登录
      if (userName === UserNameAdmin) {
        $state.go(RouteUser, {
          osId: user.osId
        })
      } else {
        $state.go(RouteUser, {
          osId: user.osId
        })
      }
    }
  }
})()
