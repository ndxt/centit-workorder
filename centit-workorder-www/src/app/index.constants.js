(function() {
  'use strict';

  const UserNameAdmin = 'admin'
  const UserNameSuperAdmin = 'super'
  const UserNameUser = 'user'

  const UserTypeSuperAdmin = 'S'
  const UserTypeAdmin = 'A'
  const UserTypeUser = 'U'

  const RouteUser = 'root.catalog'
  const RouteAdmin = 'root.catalog'

  ///////////////////////////////////////////////////////

  const ErrorNotLogin = 'NOT_LOGIN'
  const ErrorNotAllow = 'NOT_ALLOW'

  angular
    .module('workorder')

    .constant('Error', {
      ErrorNotLogin,
      ErrorNotAllow
    })

    .constant('config', {
      contextPath: '/api'
    })

    .constant('Constant', {
      UserNameAdmin,
      UserNameSuperAdmin,
      UserNameUser,
      UserTypeSuperAdmin,
      UserTypeAdmin,
      UserTypeUser,
      RouteUser,
      RouteAdmin
    })
})()
