// 动态待办
import {apiFactory} from '../index'

const api = apiFactory('appr')
const apiPayload = apiFactory('appr', {}, true)

/**
 * 动态待办列表
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryDynamic({params = {}, sort = {}, page = {}} = {}) {
  return api.get('workflow/flowExtend/listUserDynamicTasks', {
    params: {
      ...params,
      ...sort,
      ...page,
      userCode: 'U0000019',
    },
  }).then(res => res.data)
}

/**
 * 审批记录列表
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryDynamicCensorLog({params = {}, sort = {}, page = {}} = {}) {
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
export function queryDynamicInit(params = {}) {
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
export function queryDynamicFormDefById(params = {}) {
  return api.get('workflow/formDef/getFormDefById', {
    params: {
      ...params,
    },
  }).then(res => res.data)
}

/**
 * 发送
 */
export function queryDynamicTeam(params = {}) {
  return apiPayload.post('workflow/appr/loadTeamConfigData', {
    ...params,
  }).then(res => res.data)
}

/**
 * 引擎
 */
export function queryDynamicEngine(params = {}) {
  return apiPayload.post('workflow/appr/loadEngineConfigData', {
    ...params,
  }).then(res => res.data)
}

/**
 * 关注
 */
export function queryDynamicAttention(params = {}) {
  return apiPayload.post('workflow/appr/loadAttentionConfigData', {
    ...params,
  }).then(res => res.data)
}

/**
 * 主办、协办
 */
export function queryDynamicOrg(params = {}) {
  return apiPayload.post('workflow/appr/loadOrgConfigData', {
    ...params,
  }).then(res => res.data)
}

/**
 * 审批
 */
export function queryDynamicSubmit(params = {}) {
  return apiPayload.post('workflow/appr/submitModules', {
    ...params,
  }).then(res => res.data)
}
