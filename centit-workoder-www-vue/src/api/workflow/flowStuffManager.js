import {apiFactory} from '../index'

const api = apiFactory('appr')
const apiJson = apiFactory('appr', {}, true)

/**
 * 获取材料维护列表
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<any>|Promise<any>}
 */
export function queryFlowStuff({params = {}, sort = {}, page = {}} = {}) {
  return api.get('apprflow/stuff/list', {
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
export function getFlowStuff(code) {
  return api.get(`apprflow/stuff/${code}`).then(res => res.data)
}

/**
 * 新增材料
 * @param params
 * @returns {*|PromiseLike<any>|Promise<any>}
 */
export function addFlowStuff(params) {
  return apiJson.post('apprflow/stuff', params).then(res => res.data)
}

/**
 * 更新材料
 * @param params
 * @returns {Promise<T>}
 */
export function saveFlowStuff(params) {
  return apiJson.put('apprflow/stuff', params).then(res => res.data)
}

/**
 * 删除材料
 * @param code
 * @returns {*|PromiseLike<any>|Promise<any>}
 */
export function removeFlowStuff(code) {
  return api.delete(`apprflow/stuff/${code}`).then(res => res.data)
}
