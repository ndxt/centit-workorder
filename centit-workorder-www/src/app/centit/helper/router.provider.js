/* eslint-disable angular/on-watch */
;(function () {
  'use strict'

  angular.module('centit.helper')
    .provider('routerHelper', routerHelperProvider)

  /** @ngInject */
  function routerHelperProvider ($stateProvider) {
    const config = {
      mainTitle: undefined,   // 主标题
      bodyClass: 'default',   // 默认class
      layout: 'row',          // 默认布局
      showLoading: true,      // 显示等待图标
      resolveAlways: {}
    }

    this.configure = function (cfg) {
      Object.assign(config, cfg)
    }

    this.$get = RouterHelper
    /** @ngInject */
    function RouterHelper ($rootScope, $timeout, $state, Authentication, Error) {

      let handlingStateChangeError = false
      let stateCounts = {
        changes: 0,
        errors: 0
      }

      init()

      return {
        addRouterStates
      }

      function init () {
        whenStateChangeStart()
        whenStateChangeSuccess()
        handleRoutingErrors()
      }

      /**
       * 添加业务路由
       * @param states
       */
      function addRouterStates (states) {
        let data
        let config
        states.forEach(state => {
          config = state.config
          config.resolve = config.resolve || {}
          data = config.data
          addResolve(data, config.resolve)

          $stateProvider.state(state.state, config)
        })
      }

      function addResolve(data, resolves) {
        if (data.requireLogin) {
          resolves.requireLogin = function() {
            return Authentication.hasLogin()
          }
        }

        if (data.requireAuthentication) {
          resolves.requireAuthentication = ['$stateParams', function($stateParams) {
            return Authentication.canAccess($stateParams.osId)
          }]
        }
      }

      /**
       * 开始跳转路由
       */
      function whenStateChangeStart () {
        $rootScope.$on('$stateChangeStart',
          function (event, toState, toParams, fromState, fromParams) {
            let data = toState.data || {}
            let showLoading = data.showLoading

            // 显示配置false才不显示loading
            if (showLoading !== false && config.showLoading === true) {
              _showLoading(true)
            } else if (showLoading === true && config.showLoading === false) {
              _showLoading(true)
            }
          }
        )
      }

      /**
       * 处理路由跳转错误
       */
      function handleRoutingErrors () {
        $rootScope.$on('$stateChangeError',
          function (event, toState, toParams, fromState, fromParams, error) {
            _showLoading(false)

            if (handlingStateChangeError) {
              return
            }
            stateCounts.errors++
            handlingStateChangeError = true

            if (error === Error.ErrorNotAllow) {
              $state.go('blank.401')
            } else if (error === Error.ErrorNotLogin) {
              $state.go('blank.login')
            } else {
              $state.go('blank.500')
            }
          }
        )
      }

      /**
       * 处理路由跳转成功
       */
      function whenStateChangeSuccess () {
        $rootScope.$on('$stateChangeSuccess',
          function (event, toState, toParams, fromState, fromParams) {
            let data = toState.data || {}
            stateCounts.changes++
            handlingStateChangeError = false

            if (!toState.data.doNotCache) {
              $state.prev = {
                name: toState.name,
                params: toParams
              }
            }
            _showLoading(false)
            _updateDocTitle(data.title)
            _updateBodyClass(data.bodyClass)
            _updateContainerLayout(data.layout)
          }
        )

        function _updateDocTitle (title) {
          let mainTitle = config.mainTitle || ''
          title = title ? `${title} - ${mainTitle}` : mainTitle
          $rootScope.__title = title
        }

        function _updateBodyClass (cls) {
          $rootScope.__class = cls || config.bodyClass
        }

        function _updateContainerLayout (layout) {
          $rootScope.__layout = layout || config.layout
        }
      }

      /**
       * 显示等待图标
       * @param flag
       * @private
       */
      function _showLoading (flag) {
        // 让等待图标可以多显示一会
        if (flag === true) {
          $rootScope.__loading = true
        } else {
          $timeout(() => $rootScope.__loading = false, 1000)
        }

        // $rootScope.__loading = flag
      }

    }
  }
})()
