import { apiFactory } from '../index'

const api = apiFactory('appr')
// const apiJson = apiFactory('appr', {}, true)

/**
 * 获取文档列表
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
