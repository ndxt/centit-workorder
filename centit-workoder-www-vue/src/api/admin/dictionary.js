// 数据字典相关 API

import { apiFactory } from '../index'
// const api = apiFactory('appr')
const api = apiFactory('admin')

/**
 * 数据字典列表查询
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryDictionary({params = {}, sort = {}, page = {}} = {}) {
  return api.get('/system/dictionary', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 判断数据字典编码唯一
 * @param catalogCode
 * @returns {*}
 */
export function checkDictionaryCodeUnique(catalogCode) {
  if (!catalogCode) return true

  return api.get(`/system/dictionary/notexists/${catalogCode}`)
    .then(res => res.data)
}

/**
 * 获取数据字典信息
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function getDictionary(id) {
  return api.get(`/system/dictionary/${id}`)
    .then(res => res.data)
}

/**
 * 新增数据字典
 * @param dic
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function addDictionary(dic) {
  return api.post('/system/dictionary', dic)
    .then(res => res.data)
}

/**
 * 修改数据字典
 * @param id
 * @param dic
 * @returns {Promise<T | never>}
 */
export function modifyDictionary(id, dic) {
  return api.put(`/system/dictionary/update/${id}`, dic)
    .then(res => res.data)
}

/**
 * 修改数据字典明细
 * @param id
 * @param dic
 * @returns {Promise<T | never>}
 */
export function modifyDictionaryDetails(id, dic) {
  return api.put(`/system/dictionary/update/${id}`, dic)
    .then(res => res.data)
}

/**
 * 删除数据字典
 * @param id
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function removeDictionary(id) {
  return api.delete(`/system/dictionary/${id}`)
    .then(res => res.data)
}
