import { apiFactory } from '../index'

const api = apiFactory('appr')
const apiJson = apiFactory('appr', {}, true)

/**
 * 获取通用审批模块列表
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryApprovalModule({ params = {}, sort = {}, page = {} } = {}) {
  return api.get('apprflow/module/list', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 获取某一个通用审批模块
 * @param code
 */
export function getApprovalModule(code) {
  return api.get(`apprflow/module/${code}`).then(res => res.data)
}

/**
 * 新增通用审批模块
 * @param params
 */
export function addApprovalModule(params) {
  return apiJson.post('apprflow/module', params).then(res => res.data)
}

/**
 * 更新通用审批模块
 * @param params
 * @returns {Promise<T>}
 */
export function saveApprovalModule(params) {
  return apiJson.put('apprflow/module', params).then(res => res.data)
}

/**
 * 删除一个通用审批模块
 * @param id
 */
export function removeApprovalModule(code) {
  return api.delete(`apprflow/module/${code}`).then(res => res.data)
}

/**
 * 获取数据字典列表
 */
export function getDictionary() {
  return api.get(`apprflow/module/dictionary`).then(res => res.data)
}

/**
 * 获取所有流程信息
 */
export function getAllFlow() {
  return api.get(`apprflow/module/allflow`).then(res => res.data)
}

/**
 * 获取所有流程业务
 */
export function getAllOptinfo() {
  return api.get(`apprflow/module/alloptinfo`).then(res => res.data)
}

/**
 * 获取所有材料信息
 */
export function getAllStuff() {
  return api.get(`apprflow/stuff/allstuff`).then(res => res.data)
}

/**
 * 获取所有公文信息
 */
export function getAllDocument() {
  return api.get(`apprflow/document/alldocument`).then(res => res.data)
}

/**
 * 流程实例分页查询
 */
export function queryWatchList() {
  return api.get(`apprflow/watch/list`).then(res => res.data)
}

/**
 * 根据流程实例id查询流程节点操作日志
 * @param flowInstId
 */
export function queryOptidea(flowInstId) {
  return api.get(`apprflow/watch/optidea/${flowInstId}`).then(res => res.data)
}
