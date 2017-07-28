;(function () {
  'use strict'

  angular.module('centit.service')
    .factory('CacheService', CacheService)

  /** @ngInject */
  function CacheService ($http, $rootScope) {
    const urls = {}

    return {
      getFromStorage,
      requestStorage
    }

    function getFromStorage (key) {
      if (!sessionStorage.getItem(key)) {
        requestStorage(key)
      }

      return sessionStorage.getItem(key)
    }

    function requestStorage (key) {
      $http.get(urls[key]).then(function (data) {
        sessionStorage.setItem(key, data.data)
      }).error(function () {
        $rootScope.showMsg('请求失败')
      })
    }

  }
})()
