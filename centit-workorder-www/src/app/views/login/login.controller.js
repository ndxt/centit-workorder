(function() {
  'use strict'

  angular.module('workorder')
    .controller('LoginController', LoginController)

  /**  @ngInject */
  function LoginController($state, LoginAPI, OsAPI, Authentication, Constant) {
    const vm = this

    vm.user = {
      // username: Constant.UserNameAdmin,
      // password: '000000',
      // userType: Constant.UserTypeUser,
      osId: 'FILE_SVR'
    }

    vm.login = login

    activate()

    function activate() {
      // Authentication.get()
      //   .then(function (user) {
      //     vm.user = user ? angular.extend({}, DefaultUser, {userType: user.userType, osId: user.osId}) : DefaultUser;
      //     login(vm.user)
      //   })
      LoginAPI.currentuser().$promise
        .then(function (user) {
           if(user.userCode){

             afterLogin(user)
           }
        })
      queryOs()
    }

    /**
     * 模拟登录请求
     * @param user
     * @returns {boolean}
     */
    function login(user) {
      const userType = user.userType
      const osId = user.osId

      return LoginAPI.login(user).$promise

        // 保存用户信息
        .then(user => Authentication.save(angular.extend(user.userInfo, {userType, osId})))

        // 跳转页面
        .then(afterLogin)
    }

    /**
     * 登录完后的跳转
     * @param user
     */
    function afterLogin(user) {
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
    }

    /**
     * 查询系统信息
     */
    function queryOs() {
      vm.os = OsAPI.query()
    }
  }
})();
