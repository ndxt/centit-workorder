import {apiFactory} from '../index'

const api = apiFactory('appr')

/* const apiJson = apiFactory('task', {}, true) */

/**
 * 获取业务表单列表(通用审批模块)
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryApprovalBusiness({params = {}, sort = {}, page = {}} = {}) {
  return api.get('workflow/formDef/listFormDef', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data.objList)
}

/**
 * 获取某一个业务表单
 * @param code
 */
export function getApprovalBusiness(id) {
  return api.get(`workflow/formDef/getFormDefById?id=${id}`).then(res => res.data)
}

/**
 }
 * 新增业务表单
 * @param params
 */
export function addApprovalBusiness(params) {
  /* return api.post('workflow/formDef/saveFormDef', params).then(res => res.data) */
  // return api.put('workflow/formDef/saveFormDef', {
  return api.post('workflow/formDef/saveFormDef', {
    ...params,
  }).then(res => res.data)
}

/**
 * 删除一个业务表单
 * @param id
 */
export function removeApprovalBusiness(id) {
  return api.get(`workflow/formDef/deleteFormDef?id=${id}`).then(res => res.data)
}
