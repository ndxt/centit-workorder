(function() {
  'use strict'

  angular.module('workorder')
    .factory('Authentication', Authentication)

  /** @ngInject */
  function Authentication($q, Error, Constant) {
    const name = 'Workorder_User'
    let _user_

    return {
      save,
      clear,
      get,
      canAccess,
      hasLogin,
      isAdmin
    }

    function save(user) {
      if (!user) return clear()

      _user_ = user
      return $q.resolve(_user_)
    }

    function clear() {
      _user_ = null
      return $q.resolve(_user_)
    }

    function get() {
      return $q.resolve(_user_)
    }

    function canAccess(osId) {
      return hasLogin()
        .then(user => {
          if (user.osId !== osId) {
            return $q.reject(Error.ErrorNotAllow)
          }

          return user
        })
    }

    function hasLogin() {
      return get()
        .then(user => {
          if (!user) {
            return $q.reject(Error.ErrorNotLogin)
          }

          return user
        })
    }

    function isAdmin(isSuper = false) {
      return hasLogin()
        .then(user => {

          if (isSuper && user.userType !== Constant.UserTypeSuperAdmin) {
            return $q.reject(Error.ErrorNotAllow)
          } else if (user.userType !== Constant.UserTypeAdmin) {
            return $q.reject(Error.ErrorNotAllow)
          }

          return user
        })
    }
  }
})()
