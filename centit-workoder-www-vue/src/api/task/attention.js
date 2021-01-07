// 我的关注
import {apiFactory} from '../index'

const api = apiFactory('appr')

/**
 * 我的关注列表
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryAttention({params = {}, sort = {}, page = {}} = {}) {
  return api.get('workflow/flowExtend/listUserAttentions', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 材料列表
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryAttentionMaterial({params = {}, sort = {}, page = {}} = {}) {
  return api.get('workflow/appr/getStuffInfoList', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 办理日志列表
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryAttentionHandleLog({params = {}, sort = {}, page = {}} = {}) {
  return api.get('workflow/appr/getOptIdeaList', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}
