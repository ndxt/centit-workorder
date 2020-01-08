import iView from 'iview'
import Router from 'vue-router'
import store from '../store'
import workorder from './workorder'
import dictionary from './dictionary'
import role from './role'
import login from './login'
import dashboard from './dashboard'
import user from './user'
import unit from './unit'
import menu from './menu'
import file from './file'
import demo from './demo'
import business from './business'
import workflow from './workflow'
import task from './task'
import log from './log'
import temp from './temp'
import shanxi from './shanxi'
import {
  checkLogin,
  goLogin
} from '@/api/login'

// const knowledgeBase = () => import('@/modules/knowledgeBase/knowledgeBase')
const PageKnowedgeBase = () => import('@/modules/knowedgeBase/PageKnowedgeBase')
const routers = new Router({
  // mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/dashboard',
      meta: {
        title: '首页',
      },
    },

    ...temp,

    ...dashboard,

    ...login,

    ...dictionary,

    ...log,

    ...user,

    ...unit,

    ...role,

    ...menu,

    ...file,

    ...demo,

    ...business,

    ...workflow,

    ...task,

    ...shanxi,
    {
      path: '/knowledgeBase',
      name: 'knowledgeBase',
      component: PageKnowedgeBase,
      meta: {
        title: '流程定义',
      },
    },

    ...workorder

  ]
})

let confirm = false
routers.beforeEach((to, from, next) => {
  iView.LoadingBar.start()

  const {needLogin} = to.meta

  if (needLogin === false) {
    return next()
  }

  checkLogin().then(result => {
    if (result) {
      return next()
    }

    goLogin(confirm)
    confirm = true
    routers.$lastTo = to
    iView.LoadingBar.finish();
  })
})

routers.afterEach(route => {
  iView.LoadingBar.finish();
  store.commit('routeLoaded')
})

export default routers
