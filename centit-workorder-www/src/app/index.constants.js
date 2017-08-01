/* global malarkey:false */
(function() {
  'use strict';

  const UserNameAdmin = 'admin'
  const UserNameUser = 'user'

  const UserTypeAdmin = 'A'
  const UserTypeUser = 'U'

  const RouteUser = 'root.catalog'

  angular
    .module('workorder')
    .constant('config', {
      contextPath: '/api'
    })
    .constant('Constant', {
      UserNameAdmin,
      UserNameUser,
      UserTypeAdmin,
      UserTypeUser,
      RouteUser
    })
})();
