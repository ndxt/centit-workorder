import { apiFactory } from '../index'

const api = apiFactory('workflow')

/**
 * list流程
 * @param params
 * @returns {Promise<T>}
 */
export function queryFlowDefine({params = {}, sort = {}, page = {}} = {}) {
  return api.get('workflow/flow/define/listFlow', {
    params: {
      ...params,
      ...sort,
      ...page,
    },

  }).then(res => res.data)
}

/**
 * 根据flowcode获取单个流程信息（包含流程信息）
 * @param params
 * @returns {Promise<T>}
 */
export function queryFlowDefineByCode(flowCode) {
  return api.get(`workflow/flow/define/draft/${flowCode}`)
    .then(res => res.data)
}

/**
 * 删除流程
 * @param params
 * @returns {Promise<T>}
 */
export function deleteFlow(data) {
  return api.get(`workflow/flow/define/deleteFlow/${data.flowCode}`)
    .then(res => res.data)
}

/**
 * 发布流程
 * @param params
 * @returns {Promise<T>}
 */
export function publishFlow(data) {
  return api.post(`workflow/flow/define/publish/${data.flowCode}`)
    .then(res => res.data)
}

/**
 * 获取流程图所有版本
 * @param params
 * @returns {Promise<T>}
 */
export function queryAllVersions(data) {
  return api.get(`workflow/flow/define/allversions/${data.flowCode}`)
    .then(res => res.data)
}

/**
 * 修改流程图版本为当前版本
 * @param params
 * @returns {Promise<T>}
 */
export function changeFlowVersion(data) {
  return api.post(`workflow/flow/define/editfromthis/${data.flowCode}/${data.version}`)
    .then(res => res.data)
}

/**
 * 获取新的流程
 * @returns {Promise<T>}
 */
export function createNewFlow() {
  return api.get(`workflow/flow/define/create`)
    .then(res => res.data)
}

/**
 * 获取业务模块
 * @returns {Promise<T>}
 */
export function listFlowOptInfo() {
  return api.get(`workflow/flow/opt/allOptInfos`)
    .then(res => res.data)
}

/**
 * 新增流程图
 * @param data
 * @returns {*}
 */
export function addFlowDefine(data) {
  return api.post(`workflow/flow/define/${data.flowCode}`, data)
}

/**
 * 复制流程图
 * @param data
 * @returns {*}
 */
export function copyFlowDefine(data) {
  return api.post(`workflow/flow/define/${data.oldFlowCode}/${data.doCopyXML}`, data)
}

/**
 * 新增流程图-阶段
 * @param data
 * @returns {*}
 */
export function addFlowDefineStage(data) {
  return api.post(`workflow/flow/define/stage/${data.flowCode}`, data)
  // return api.put(`workflow/flow/define/stage/${data.flowCode}`, data)
}

/**
 * 新增流程图-角色
 * @param data
 * @returns {*}
 */
export function addFlowDefineRole(data) {
  return api.post(`workflow/flow/define/role/${data.flowCode}`, data)
}

/**
 * 新增流程图-变量
 * @param data
 * @returns {*}
 */
export function addFlowDefineVariable(data) {
  return api.post(`workflow/flow/define/variableDefine/${data.flowCode}`, data)
  // return api.put(`workflow/flow/define/variableDefine/${data.flowCode}`, data)
}
