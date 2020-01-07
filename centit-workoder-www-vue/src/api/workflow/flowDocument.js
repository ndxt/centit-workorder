import { apiFactory } from '../index'

const api = apiFactory('appr')
const apiJson = apiFactory('appr', {}, true)

/**
 * 获取材料维护列表
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<any>|Promise<any>}
 */
export function queryFlowDocument({params = {}, sort = {}, page = {}} = {}) {
  return api.get('apprflow/document/list', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 根据材料代码code获取材料详情
 * @param code
 * @returns {*|PromiseLike<any>|Promise<any>}
 */
export function getFlowDocument(code) {
  return api.get(`apprflow/document/${code}`).then(res => res.data)
}

/**
 * 新增材料
 * @param params
 * @returns {*|PromiseLike<any>|Promise<any>}
 */
export function addFlowDocument(params) {
  return apiJson.post('apprflow/document', params).then(res => res.data)
}

/**
 * 更新材料
 * @param params
 * @returns {Promise<T>}
 */
export function saveFlowDocument(params) {
  return apiJson.put('apprflow/document', params).then(res => res.data)
}

/**
 * 删除材料
 * @param code
 * @returns {*|PromiseLike<any>|Promise<any>}
 */
export function removeFlowDocument(code) {
  return api.delete(`apprflow/document/${code}`).then(res => res.data)
}
