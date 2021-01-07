// 用户管理
import {apiFactory} from '../index'

const api = apiFactory('admin')

/**
 * 用户管理列表查询
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryUser({params = {}, sort = {}, page = {}} = {}) {
  return api.get('/system/userinfo', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 获取登录用户部门下的用户
 * @param tag
 * @returns {*}
 */
export function queryDepartmentUser(tag) {
  return api.get(`/system/unitinfo/currentusers/${tag}`)
    .then(res => res.data)
}

/**
 * 判断登录名唯一
 * @param catalogCode
 * @returns {*}
 */
export function checkUserLoginNameUnique(loginName) {
  if (!loginName) return true

  const params = {
    loginName
  }
  return queryUser({params})
    .then(data => data.length === 0)
}

/**
 * 新增用户
 * @param user
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function addUser(user) {
  return api.post('/system/userinfo', user).then(res => res.data)
}

/**
 * 查看用户权限信息
 * @param params
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export async function getUserRight(params = {}) {
  return api.get(`/system/optinfo/userpoweropts/${params.userCode}`)
    .then(res => LispRightData(res.data))
}

// 重组用户权限树
export function LispRightData(data) {
  data.forEach(function (value, i) {
    data[i].title = data[i].text
    if (data[i].children) {
      LispRightData(data[i].children)
    }
  })
  return data
}

/**
 * 获取用户信息
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function getUser(id) {
  return api.get(`/system/userinfo/${id}`)
    .then(res => Object.assign({userInfo: {}, userUnit: { userRank: 'YG', userStation: 'ZY', }}, res.data))
}

/**
 * 编辑用户
 * @param user
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function modifyUser(id, user) {
  return api.put(`/system/userinfo/${id}`, user).then(res => res.data)
}

/**
 * 删除用户
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function removeUser(id) {
  return api.delete(`/system/userinfo/${id}`).then(res => res.data)
}

/**
 * 获取用户设置
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function getUserSetting({params = {}, sort = {}, page = {}} = {}) {
  return api.get(`/system/usersetting/list/${params.id}`, {
    params: {
      ...sort,
      ...page,
    }
  })
    .then(res => res.data)
}

/**
 * 获取用户角色-继承角色
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */

export function getUserRolesInherited({params = {}, sort = {}, page = {}} = {}) {
  return api.get(`/system/userrole/userrolesinherited/${params.id}`, {
    params: {
      ...sort,
      ...page,
    }
  }).then(res => res.data)
}
/**
 * 获取用户角色-自有角色
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function getUserRolesOwn({params = {}, sort = {}, page = {}} = {}) {
  return api.get(`/system/userrole/userroles/${params.id}`, {
    params: {
      ...sort,
      ...page,
    }
  }).then(res => res.data)
}

/**
 * 添加用户角色-自有角色
 * @param params
 */
export function addUserRoleOwn(params = {}) {
  return api.post('/system/userrole', {
    ...params,
  }).then(res => res.data)
}

/**
 * 删除用户角色-自有角色
 * @param user
 */
export function removeUserRoleOwn(user) {
  return api.put(`/system/userrole/ban/${user.roleCode}/${user.userCode}`)
    .then(res => res.data)
}
/**
 * 获取用户机构列表
 * @param user
 */
export function getUserUnitList({params = {}, sort = {}, page = {}} = {}) {
  return api.get(`/system/userunit/userunits/${params.id}`, {
    params: {
      ...sort,
      ...page,
    }
  }).then(res => res.data)
}

/**
 * 添加用户机构
 * @param params
 */
export function addUserUnit(params = {}) {
  return api.post('/system/userunit', {
    ...params,
  }).then(res => res.data)
}

/**
 * 获取用户机构信息
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function getUserUnit(id) {
  return api.get(`/system/userunit/${id}`)
    .then(res => res.data)
}

/**
 * 编辑用户机构信息
 * @param user
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function EditUserUnit(id, user) {
  return api.put(`/system/userunit/${id}`, user).then(res => res.data)
}

/**
 * 删除用户机构
 * @param user
 */
export function removeUserUnit(id) {
  return api.delete(`/system/userunit/${id}`)
    .then(res => res.data)
}

/**
 * 重置用户密码
 * @param user
 */
export function resetUserPassWord(params) {
  return api.put('/system/userinfo/reset', {
    ...params,
  }).then(res => res.data)
}

/**
 * 部门用户管理列表查询
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryDept({params = {}, sort = {}, page = {}} = {}) {
  return api.get('/system/unitinfo/currentunit/users', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 获取部门用户机构信息
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function getDeptUser(id) {
  return api.get(`/system/userinfo/${id}`)
    .then(res => res.data)
}

/**
 * 获取部门用户角色-部门角色
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function getDeptUserOwn({params = {}, sort = {}, page = {}} = {}) {
  return api.get(`/system/userrole/usercurrentroles/${params.id}`, {
    params: {
      ...sort,
      ...page,
    }
  }).then(res => res.data)
}

/**
 * 与ldap同步
 * @param directory
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function getLdap(directory) {
  return api.get(`/system/datamanager/syncuserdirectory/${directory}`)
    .then(res => res.data)
}
