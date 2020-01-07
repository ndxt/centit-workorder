import { apiFactory } from '../index'

const api = apiFactory('workflow')
const apiJson = apiFactory('workflow', {}, true)
const apiForm = apiFactory('metaform')

/**
 * 获取任务待办列表
 * @param params
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryFlowTask({params = {}, sort = {}, page = {}} = {}) {
  return api.get('workflow/flow/engine/listUserTasks', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 全部转移
 */
export function moveFlowTaskTransfer(params = {}) {
  return apiJson.post('workflow/flow/manager/moveUserTaskTo', {
    ...params,
  }).then(res => res.data)
}

/**
 * 部分转移
 */
export function moveFlowTaskTransferSelect(params = {}) {
  return apiJson.post('workflow/flow/manager/moveSelectedUserTaskTo', {
    ...params,
  }).then(res => res.data)
}

/**
 * 获取任务委托列表
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryFlowTaskEntrust({params = {}, sort = {}, page = {}} = {}) {
  return api.get(`workflow/flow/relegate/byUser/${params.userCode}`, {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data.objList)
}

/**
 * 通过参数获取委托 // 不知道这个什么意思
 */
export function getFlowTaskEntrust(params = {}) {
  return api.post(`workflow/flow/relegate/get/${params.json}`).then(res => res.data)
}

/**
 * 获取用户角色
 */
export function getFlowTaskUserRole(params = {}) {
  return api.get(`workflow/flow/relegate/role/${params.userCode}`).then(res => res.data)
}

/**
 * 新增任务委托
 */
export function addFlowTaskEntrust(params = {}) {
  return apiJson.post('workflow/flow/relegate', {
    ...params,
  }).then(res => res.data)
}

/**
 * 保存任务委托
 */
export function saveFlowTaskEntrust(params = {}) {
  return api.put('workflow/flow/relegate', {
    ...params,
  }).then(res => res.data)
}

/**
 * 删除任务委托
 */
export function removeFlowTaskEntrust(params = {}) {
  return api.delete(`workflow/flow/relegate/${params.relegateNo}`).then(res => res.data)
}

/**
 * 获取一个数据带子表，主键作为参数以key-value形式提交
 */
export function getFormaccess(params = {}) {
  return apiForm.get(`formaccess/${params.modelId}?flowInstId=${params.flowInstId}`).then(res => res.data)
}
