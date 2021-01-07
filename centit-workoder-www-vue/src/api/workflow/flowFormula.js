import {apiFactory} from '../index'

const api = apiFactory('workflow')
const apiJson = apiFactory('workflow', {}, true)

/**
 * 获取权限表达式列表
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryFlowFormula({params = {}, sort = {}, page = {}} = {}) {
  return api.get('workflow/formula', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data.objList)
}

/**
 * 获取单个权限表达式
 */
export function getFlowFormula(params = {}) {
  return api.get(`workflow/formula/${params.formulaCode}`).then(res => res.data)
}

/**
 * 新增权限表达式
 */
export function addFlowFormula(params = {}) {
  return apiJson.post('workflow/formula', {
    ...params,
  }).then(res => res.data)
}

/**
 * 修改权限表达式
 */
export function saveFlowFormula(params = {}) {
  return apiJson.post('workflow/formula', {
    ...params,
  }).then(res => res.data)
}

/**
 * 预览权限表达式对应用户
 */
export function viewFlowFormulaUser(params = {}) {
  return api.get(`workflow/formula/calcUsers?formula=${params.formula}`).then(res => res.data)
}

/**
 * 删除权限表达式
 */
export function removeFlowFormula(params = {}) {
  return api.post(`workflow/formula/${params.formulaCode}`).then(res => res.data)
}
