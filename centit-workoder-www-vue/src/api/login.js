import _ from 'lodash'
import router from '../router'

import {Modal,} from 'iview'
import {apiFactory,} from './index'

const api = apiFactory('admin')

export const goLogin = _.debounce((confirm) => {
  if (confirm === false) {
    return router.replace({
      name: 'login',
    })
  }

  Modal.warning({
    title: '登录',
    content: '您所访问的页面需要登录，即将跳转到登录页面。',
    onOk() {
      router.replace({
        name: 'login',
      })
    },
  })
}, 400)

export function checkLogin() {
  return api.get('/system/mainframe/hasLogin')
    .then(res => res.data)
}

/**
 * 普通登录
 * @param username
 * @param password
 * @returns {*|PromiseLike<T>|Promise<T>}
 */
export function login({username, password}) {
  return api.post('/login', {
    password,
    username,
    ajax: true,
  }).then(res => res.data)
}

/**
 * 管理员登录
 * @param username
 * @param password
 * @returns {*|PromiseLike<T>|Promise<T>}
 */
export function loginAsAdmin({username, password}) {
  return api.post('/loginasadmin', {
    password,
    username,
    ajax: true,
  }).then(res => res.data)
}

/**
 * 获取当前用户信息
 */
export function getCurrentuser() {
  return api.get('/system/mainframe/currentuser')
    .then(res => res.data)
}

export function checkpwd(password) {
  return api.post('/system/mainframe/checkpwd', {
    ...password,
    ajax: true,
  }).then(res => res.data)
}

export function changepwd(user) {
  return api.put(`/system/mainframe/changepwd`, user)
    .then(res => res.data)
}

/**
 * 获取当前用户岗位信息
 */
export function queryPositions() {
  return api.get('system/mainframe/userstations')
    .then(res => {
      return res.data.map(m => {
        return {
          ...m,
          valueField: `${m.userStation} - ${m.userRank} - ${m.unitCode}`,
        }
      })
    })
}

export function getCurrposition() {
  return api.get('system/mainframe/usercurrstation')
    .then(res => res.data)
}

export function setPosition(id) {
  return api.put(`/system/mainframe/setuserstation/${id}`)
    .then(res => res.data)
}
