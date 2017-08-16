(function() {
  'use strict';

  const UserNameAdmin = 'admin'
  const UserNameSuperAdmin = 'super'
  const UserNameUser = 'user'

  const UserTypeSuperAdmin = 'S'
  const UserTypeAdmin = 'A'
  const UserTypeUser = 'U'

  const RouteUser = 'root.document'
  const RouteAdmin = 'admin.document'

  ///////////////////////////////////////////////////////

  const ErrorNotLogin = 'NOT_LOGIN'
  const ErrorNotAllow = 'NOT_ALLOW'

  angular
    .module('workorder')

    .constant('ErrorMessage', {
      ErrorNotLogin,
      ErrorNotAllow
    })

    .constant('config', {
      contextPath: '/workorder'
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
})();
