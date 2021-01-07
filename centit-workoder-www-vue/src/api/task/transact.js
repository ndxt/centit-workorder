// 我的待办
import {apiFactory} from '../index'

const api = apiFactory('appr')
const apiPayload = apiFactory('appr', {}, true)
const apiAllPayload = apiFactory('', {}, true)

/**
 * 下载文件
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function downloadFileById(fileId, fileName) {
  return api.get(`workflow/download/download/` + fileId + `？fileName=` + fileName).then(res => res.data)
}

/**
 * 删除节点文件
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function deleteFileById(fileId) {
  return api.get(`workflow/upload/delete/` + fileId).then(res => res.data)
}
/**
 * 节点文件列表
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryNodeInstFiles(nodeInstId) {
  return api.get(`workflow/optStuffInfo/getNodeInstFiles/` + nodeInstId).then(res => res.data)
}

/**
 }
 * 我的待办列表
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryTransact({params = {}, sort = {}, page = {}} = {}) {
  return api.get('workflow/flowExtend/listUserTasks', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 我的待办单个数据
 * @param params
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function getTransact(params = {}) {
  return api.get(`workflow/flowExtend/listUserTasks?s_nodeInstId=${params.s_nodeInstId}&userCode=${params.s_userCode}`)
    .then(res => res.data)
}

/**
 * 审批记录列表
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryTransactCensorLog({params = {}, sort = {}, page = {}} = {}) {
  return api.get('workflow/appr/getOptIdeaList', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 审批初始化
 * @param params
 */
export function queryTransactInit(params = {}) {
  return api.get('workflow/appr/getModuleDataToJson', {
    params: {
      ...params,
    },
  }).then(res => res.data)
}

/**
 * 业务页面
 * @param params
 */
export function queryTransactFormDefById(params = {}) {
  return api.get('workflow/formDef/getFormDefById', {
    params: {
      ...params,
    },
  }).then(res => res.data)
}

/**
 * 发送
 */
export function queryTransactTeam(params = {}) {
  return apiPayload.post('workflow/appr/loadTeamConfigData', {
    ...params,
  }).then(res => res.data)
}

/**
 * 引擎
 */
export function queryTransactEngine(params = {}) {
  return apiPayload.post('workflow/appr/loadEngineConfigData', {
    ...params,
  }).then(res => res.data)
}

/**
 * 关注
 */
export function queryTransactAttention(params = {}) {
  return apiPayload.post('workflow/appr/loadAttentionConfigData', {
    ...params,
  }).then(res => res.data)
}

/**
 * 主办、协办
 */
export function queryTransactOrg(params = {}) {
  return apiPayload.post('workflow/appr/loadOrgConfigData', {
    ...params,
  }).then(res => res.data)
}

/**
 * 审批
 */
export function queryTransactSubmit(params = {}) {
  return apiPayload.post('workflow/appr/submitModules', {
    ...params,
  }).then(res => res.data)
}

/**
 * 审批
 */
export function queryTransactSave(params = {}) {
  return apiPayload.post('workflow/appr/saveModule', {
    ...params,
  }).then(res => res.data)
}

export function businessFormSubmit(url, params = {}) {
  return apiAllPayload.post(url, {
    ...params,
  }).then(res => res.data)
}

/**
 * 审批初始化
 * @param params
 */
export function queryFlowView(flowid) {
  return api.get('workflow/appr/queryFlowView/' + flowid).then(res => res.data)
}

/** 文件上传 */

export function uploadWorkflowFile(file, params, {onUploadProgress} = {}) {
  return api.post('workflow/upload/upload', file, {
    params: {
      token: file.token,
      name: file.name,
      size: file.size,
      ...params,
    },

    onUploadProgress,

    headers: {
      'Content-Type': 'application/octet-stream',
    },
  }).then(res => res.data)
}
