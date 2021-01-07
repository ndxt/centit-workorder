// 审批角色
import {addSearchParamsPrefix, apiFactory} from '../index'

const api = apiFactory('workflow')
const apiJson = apiFactory('workflow', {}, true)

/**
 * 获取审批角色列表
 * @param params
 * @param sort
 * @param page
 */
export function queryFlowRoleList({params = {}, sort = {}, page = {}} = {}) {
  return api.get('workflow/appr/role', {
    params: {
      ...addSearchParamsPrefix(params),
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 删除审批角色
 * @param roleCode
 */
export function removeFlowRole(roleCode) {
  return api.delete(`workflow/appr/role/${roleCode}`).then(res => res.data)
}

/**
 * 删除审批角色明细
 * @param id
 */
export function removeFlowRoleDefineList(id) {
  return api.delete(`workflow/appr/role/define/${id}`).then(res => res.data)
}

/**
 * 获取某一个审批角色明细(新增一个操作)
 * @param id
 */
export function addFlowRoleDefine(params) {
  return apiJson.put('workflow/appr/role/define/list', params).then(res => res.data)
}

/**
 * 获取某一个审批角色明细
 * @param roleCode
 */
export function queryFlowRoleDefineList(roleCode) {
  return api.get(`workflow/appr/role/define/${roleCode}`).then(res => res.data)
}

/**
 * 新增审批角色
 * @param params
 */
export function addFlowRole(params) {
  return apiJson.post('workflow/appr/role', params).then(res => res.data)
}

/**
 * 编辑审批模块
 * @param params
 * @returns {*}
 */
export function editFlowRole(params) {
  return apiJson.put('workflow/appr/role', params).then(res => res.data)
}

/**
 * 同步审批角色到权限表达式
 * @returns {*|PromiseLike<any>|Promise<any>}
 */
export function syncFlowRole() {
  return api.get('workflow/appr/role/sync').then(res => res.data)
}
