// 用户管理
import {apiFactory} from '../index'

const api = apiFactory('admin')
const apiJson = apiFactory('admin', {}, true)

/**
 * 数据库列表查询
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryDatabase({params = {}, sort = {}, page = {}} = {}) {
  return api.get('/system/sys/database', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}
/**
 * 新增数据库
 * @param user
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function addDatabase(database) {
  return api.post('/system/sys/database', database).then(res => res.data)
}

/**
 * 获取数据库信息
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function getDatabase(id) {
  return api.get(`/system/sys/database/${id}`)
    .then(res => res.data)
}

/**
 * 编辑数据库
 * @param user
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function modifyDatabase(id, database) {
  return api.put(`/system/sys/database/${id}`, database).then(res => res.data)
}

/**
 * 删除数据库
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function removeDatabase(id) {
  return api.delete(`/system/sys/database/${id}`).then(res => res.data)
}

/**
 * 集成业务列表查询
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryOs({params = {}, sort = {}, page = {}} = {}) {
  return api.get('/system/sys/os', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}
/**
 * 新增集成业务
 * @param user
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function addOs(database) {
  return api.post('/system/sys/os', database).then(res => res.data)
}

/**
 * 获取集成业务
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function getOs(id) {
  return api.get(`/system/sys/os/${id}`)
    .then(res => res.data)
}

/**
 * 编辑集成业务
 * @param user
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function modifyOs(id, database) {
  return api.put(`/system/sys/os/${id}`, database).then(res => res.data)
}

/**
 * 删除集成业务
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function removeOs(id) {
  return api.delete(`/system/sys/os/${id}`).then(res => res.data)
}

/**
 * 刷新单个集成业务数据
 * @param osdata
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function singleRefreshOs(osdata) {
  return apiJson.post('/system/sys/os/data/refresh/single', osdata).then(res => res.data)
}

/**
 * 刷新全部集成业务数据
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function allRefreshOs() {
  return api.get('/system/sys/os/data/refresh/all').then(res => res.data)
}

/**
 * 链接测试
 * @param osdata
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function testConnectOs(osdata) {
  return api.post('/system/sys/database/testConnect', osdata)
    .then(res => res.data)
}
