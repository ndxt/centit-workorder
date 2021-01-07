// 申请demo
import {apiFactory} from '../index'

const api = apiFactory('appr')
const apiPayload = apiFactory('appr', {}, true)

/**
 * 我的申报列表
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function loadDemoList({params = {}, sort = {}, page = {}} = {}) {
  return api.get('workflow/req/offer/loadDemoList', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 审批
 */
export function demoSubmit(params = {}) {
  return apiPayload.post('workflow/req/offer/saveDemoData', {
    ...params,
  }).then(res => res.data)
}
