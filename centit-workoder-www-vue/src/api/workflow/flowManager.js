import { apiFactory } from '../index'

const api = apiFactory('workflow')
const apiJson = apiFactory('workflow', {}, true)

/**
 * 获取流程实例管理列表
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryFlowManager({params = {}, sort = {}, page = {}} = {}) {
  return api.get('workflow/flow/manager', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 修改流程实例
 */
export function saveFlowManager(params = {}) {
  return apiJson.post('workflow/flow/manager', {
    ...params,
  }).then(res => res.data)
}

/**
 * 暂停流程实例
 */
export function pauseFlowManager(params = {}) {
  return api.get(`workflow/flow/manager/suspendinst/${params.flowInstId}`).then(res => res.data)
}

/**
 * 唤醒流程实例
 */
export function wakeFlowManager(params = {}) {
  return api.get(`workflow/flow/manager/activizeinst/${params.flowInstId}`).then(res => res.data)
}

/**
 * 强制结束流程实例
 */
export function stopFlowManager(params = {}) {
  return api.get(`workflow/flow/manager/stopinst/${params.flowInstId}`).then(res => res.data)
}

/**
 * 重新运行流程实例
 */
export function restartFlowManager(params = {}) {
  return api.put(`workflow/flow/manager/reStartFlow/${params.flowInstId}/${params.userCode}`).then(res => res.data)
}

/**
 * 查看流程图
 */
export function openFlowView(id) {
  window.open(`/api/workflow/page/workflow/flowView.html?flowInstId=${id}&contentPath=/api/workflow/`)
}

/**
 * 获取节点列表
 */
export function queryFlowManagerNode(params) {
  return api.get('workflow/flow/manager/listFlowInstNodes', {
    ...params,
  }).then(res => res.data)
}

/**
 * 节点回退
 */
export function goBackFlowManagerNode(nodeInstId) {
  return api.get(`workflow/flow/manager/nodestate/${nodeInstId}/1`).then(res => res.data)
}

/**
 * 节点分配任务
 */
export function addFlowManagerNodeTask(nodeInstId, params) {
  return api.post(`workflow/flow/manager/assign/${nodeInstId}/${params.userCode}`, params).then(res => res.data)
}

/**
 * 节点强制提交
 */
export function submitFlowManagerNode(nodeInstId) {
  return api.get(`workflow/flow/manager/nodestate/${nodeInstId}/2`).then(res => res.data)
}

/**
 * 节点重新运行
 */
export function restartFlowManagerNode(nodeInstId) {
  return api.get(`workflow/flow/manager/resetToCurrent/${nodeInstId}`).then(res => res.data)
}

/**
 * 获取流程变量列表
 */
export function queryFlowManagerVariable({params = {}, sort = {}, page = {}} = {}) {
  return api.get(`workflow/flow/manager/getvariablelist/${params.flowInstId}`, {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 新增流程变量
 */
export function addFlowManagerVariable(flowInstId, params = {}) {
  return api.get(`workflow/flow/manager/savevariable/${flowInstId}/${params.varName}/${params.varValue}?runToken=${params.runToken}`).then(res => res.data)
}

/**
 * 获取办件角色列表
 */
export function queryFlowManagerRole({params = {}, sort = {}, page = {}} = {}) {
  return api.get(`workflow/flow/manager/getteamlist/${params.flowInstId}`, {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 获取流程角色
 */
export function getFlowManagerRole(params = {}) {
  return api.get(`workflow/flow/define/itemrole/${params.flowCode}/0`).then(res => res.data)
}

/**
 * 新增办件角色
 */
export function addFlowManagerRole(flowInstId, params) {
  return api.post(`workflow/flow/manager/assignFlowWorkTeam/${flowInstId}/${params.roleCode}/${params.userCode}/${params.authDesc}`, params).then(res => res.data)
}

/**
 * 删除办件角色
 */
export function removeFlowManagerRole(flowInstId, params) {
  return api.get(`workflow/flow/manager/deleteteam/${flowInstId}/${params.roleCode}/${params.userCode}`).then(res => res.data)
}

/**
 * 获取流程机构列表
 */
export function queryFlowManagerUnit({params = {}, sort = {}, page = {}} = {}) {
  return api.get(`workflow/flow/manager/getorglist/${params.flowInstId}`, {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 新增流程机构
 */
export function addFlowManagerUnit(flowInstId, params) {
  return api.post(`workflow/flow/manager/saveorg/${flowInstId}/${params.roleCode}/${params.unitCode}/${params.authDesc}`, params).then(res => res.data)
}

/**
 * 删除流程机构
 */
export function removeFlowManagerUnit(flowInstId, params) {
  return api.get(`workflow/flow/manager/deleteorg/${flowInstId}/${params.roleCode}/${params.unitCode}`, params).then(res => res.data)
}

/**
 * 获取日志信息列表
 */
export function queryFlowManagerLog({params = {}, sort = {}, page = {}} = {}) {
  return api.get(`workflow/flow/manager/getOptLogList/${params.flowInstId}`, {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

// 其余暂未用到

/**
 * 更改所属机构
 */
export function changeUnit(code, params) {
  return api.put(`workflow/flow/manager/changeunit/${code}/${params.unitCode}`, params)
    .then(res => res.data)
}

/**
 * 节点挂起
 */
export function hangFlowManagerNode(nodeInstId) {
  return api.get(`workflow/flow/manager/nodestate/${nodeInstId}/8`).then(res => res.data)
}

/**
 * 节点唤醒
 */
export function wakeFlowManagerNode(nodeInstId) {
  return api.get(`workflow/flow/manager/nodestate/${nodeInstId}/6`).then(res => res.data)
}

/**
 * 节点操作人员
 */
export function queryFlowManagerNodeOperating({params = {}} = {}) {
  return api.get(`workflow/flow/manager/listNodeOpers/${params.nodeInstId}`).then(res => res.data)
}

/**
 * 节点任务分配
 */
export function queryFlowManagerNodeTask({params = {}} = {}) {
  return api.get(`workflow/flow/manager/listusertasks/${params.nodeInstId}`).then(res => res.data)
}

/**
 * 节点任务回收
 */
export function recoverFlowManagerNodeTask(id) {
  return api.post(`workflow/flow/manager/disableTask/${id}`).then(res => res.data)
}

/**
 * 节点任务删除
 */
export function removeFlowManagerNodeTask(nodeInstId, mangerUserCode) {
  return api.post(`workflow/flow/manager/deleteNodeTask/${nodeInstId}/${mangerUserCode}`).then(res => res.data)
}

/**
 * 节点日志
 */
export function queryNodeLogs(id) {
  return api.get(`workflow/flow/manager/nodelogs/${id}`).then(res => res.data)
}

/**
 * 查看流程
 * @param id
 */
export function openInfo(id) {
  window.open(`/#/task/flowView/${id}`)
}
