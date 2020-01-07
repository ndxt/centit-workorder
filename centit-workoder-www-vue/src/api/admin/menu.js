// 系统业务

import { apiFactory } from '../index'

const api = apiFactory('admin')

/**
 * 系统业务列表查询
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryMenu({ params = {}, sort = {}, id } = {}) {
  return api.get('/system/optinfo/sub', {
    params: {
      ...params,
      ...sort,
      id: id,
    },
  }).then(res => res.data)
}

/**
 * 系统业务详情查询
 * @param params
 * @param code
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function getMenu(optId) {
  return api.get(`/system/optinfo/${optId}`).then(res => res.data)
}

/**
 * 判断菜单编码唯一
 * @param catalogCode
 * @returns {*}
 */
export function checkMenuCodeUnique(optId) {
  return api.get(`/system/optinfo/notexists/${optId}`).then(res => res.data)
}

/**
 * 系统业务新增顶级菜单和下级菜单
 * @param menu
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function addMenu(menu) {
  return api.post('/system/optinfo', menu).then(res => res.data)
}

/**
 * 系统业务修改菜单
 * @param menu
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function modifyMenu(menu) {
  return api.put(`/system/optinfo/${menu.optId}`, menu).then(res => res.data)
}

/**
 * 系统业务菜单权限更新
 * @param menu
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function setMenuJurisdiction(menu) {
  return api.put(`/system/optinfo/editpower/${menu.optId}`, menu).then(res => res.data)
}

/**
 * 系统业务菜单删除
 * @param menu
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function removeMenu(optId) {
  return api.delete(`/system/optinfo/${optId}`).then(res => res.data)
}
