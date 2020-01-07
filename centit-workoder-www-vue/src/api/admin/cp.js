/** @module cp */

import { apiFactory } from '../index'
import {makeTree} from '../../tool'

const api = apiFactory('admin')

/**
 * 数据字典子项
 *
 * @typedef {Object} DictionaryItem#cp
 * @property {string} dictionaryCode 字典编码
 * @property {string} dictionaryName 字典名称
 * @property {string} dictionaryDesc 字典描述
 */

/**
 * @class
 */
export class DictionaryItem {
}

/**
 * 从后台缓存中获取指定编码的数据字典子项列表
 *
 * @version 1.2.3
 *
 * @async
 * @param {string} code 数据字典编码
 * @param {DictionaryItem[]} [obj]  数据字典
 * @param {string} obj.dicCode 字典编码
 * @param {string=} obj.dicName 字典名称
 * @param {string} [obj.dicDesc = ABC] 字典描述
 *
 * @example <caption>使用 Promise </caption>
 *  import {queryDictionaryDetails} from '@centit/api/admin/cp'
 *
 *  queryDictionaryDetails('LogLevel')
 *    .then(data => console.log(data))
 *
 * @example <caption>使用 async/await</caption>
 *  import {queryDictionaryDetails} from 'api/admin/cp'
 *
 *  const data = await queryDictionaryDetails('LogLevel')
 *  console.log(data)
 *
 * @returns {DictionaryItem[]} 数据字典子项列表
 */
export function queryDictionaryDetails(code, obj) {
  return api.get(`/system/cp/dictionary/${code}`, {params: obj})
    .then(res => res.data)
}

/**
 * 查询用户
 * @param tag
 * @returns {*}
 */
export function queryUser(tag = 'T') {
  return api.get(`/system/cp/alluser/${tag}`)
    .then(res => res.data)
}

/**
 * 查询机构
 * @param tag
 * @returns {*}
 */
export function queryUnit(tag = 'T') {
  return api.get(`/system/cp/allunits/${tag}`)
    .then(res => res.data)
}

/**
 * 查询机构树
 * @param tag
 * @returns {*}
 */
export function queryUnitTree(tag = 'T') {
  function isParent(p) {
    return this.parentUnit === p.unitCode
  }

  return queryUnit(tag)
    .then(units => makeTree(units, isParent))
}

/**
 * 查询菜单
 * @param tag
 * @returns {*}
 */
export function queryMenu(tag = 'A') {
  return api.get(`/system/cp/optinfo/${tag}`)
    .then(res => res.data)
}

/**
 * 查询菜单树
 * @param tag
 * @returns {*}
 */
export function queryMenuTree(tag = 'A') {
  return queryMenu(tag)
    .then(units => makeTree(units))
}

/**
 * 查询角色
 * @param tag
 * @returns {*}
 */
export function queryRole(tag = 'G') {
  return api.get(`/system/cp/roleinfo/${tag}`)
    .then(res => res.data)
}
