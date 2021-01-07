import {apiFactory} from '../index'

const api = apiFactory('workflow')
const apiJson = apiFactory('workflow', {}, true)
const apiForm = apiFactory('metaform')

/**
 * 获取某一个审模块接口的业务明细(新增一个操作)
 * @param id
 */
export function addParam(params) {
  return apiJson.put('workflow/flowModule/saveParameterList', params).then(res => res.data)
}

/**
 * 获取某一个审模块接口的业务明细(新增一个操作)
 * @param id
 */
export function addInterface(params) {
  return apiJson.put('workflow/flowModule/saveInterfaceList', params).then(res => res.data)
}

/**
 * 删除模块接口实例
 * @param id
 */
export function removeInterFacesList(interfaceCode) {
  return api.delete(`workflow/flowModule/deleteInterfaceByCode?interfaceId=${interfaceCode}`).then(res => res.data)
}

/**
 * 删除模块实例
 * @param id
 */
export function removeModuleByCode(moduleCode) {
  return api.get(`workflow/flowModule/deleteModuleByCode?moduleCode=${moduleCode}`).then(res => res.data)
}

/**
 * 获取某一个模块接口的参数明细
 * @param id
 */
export function queryParamsList(interfaceId) {
  return api.get(`workflow/flowModule/getParameterListById/${interfaceId}`).then(res => res.data)
}

/**
 * 获取某一个模块接口的业务明细
 * @param id
 */
export function queryInterFacesList(moduleCode) {
  return api.get(`workflow/flowModule/getInterfaceListByCode/${moduleCode}`).then(res => res.data)
}

/**
 * 获取到一个模块接口实例
 * @param params
 */
export function getInterFaceConfig(moduleCode) {
  return api.get(`workflow/flowModule/getModuleByCode?moduleCode=${moduleCode}`).then(res => res.data)
}

/**
 * 新增,编辑某块配置接口
 * @param params
 */
export function addInterFaceConfig(params) {
  return apiJson.post('workflow/flowModule/saveFlowModule', params).then(res => res.data)
}

/**
 * 审批流程列表查询
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryModuleInterFaceConfigs({params = {}, sort = {}, page = {}} = {}) {
  return api.get('workflow/flowModule/listFlowModule', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 审批流程列表查询
 * @param params
 * @param sort
 * @param page
 * @returns {*|PromiseLike<T | never>|Promise<T | never>}
 */
export function queryFlowBusiness({params = {}, sort = {}, page = {}} = {}) {
  return api.get('workflow/flow/opt', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 创建审批流
 */
export function getNewOptId() {
  return api.get('workflow/flow/opt/newId').then(res => res.data)
}

/**
 * 新增审批流
 * @param params
 */
export function addOptInfo(params) {
  return apiJson.post('workflow/flow/opt', params).then(res => res.data)
}

/**
 * 获取某一个审批流 和 listOptPages 没有差别
 * @param id
 */
export function getOptInfo(id) {
  return api.get(`workflow/flow/opt/${id}`).then(res => res.data)
}

/**
 * 编辑某一个审批流
 * @param params
 */
export function editOptInfo(params) {
  return apiJson.put('workflow/flow/opt', params).then(res => res.data)
}

/**
 * 删除某一个审批流
 * @param id
 */
export function removeOptInfo(id) {
  return api.delete(`workflow/flow/opt/${id}`).then(res => res.data)
}

/**
 * 根据optId获取流程页面,用于编辑包括交互的和自动运行的
 * @param id
 */
export function listOptPages(id) {
  return api.get(`workflow/flow/opt/pages/${id}`).then(res => res.data)
}

/**
 * 根据optCode获取流程页面
 * @param id
 */
export function listPageByOptCode(optCode) {
  return api.get(`workflow/flow/opt/page/${optCode}`).then(res => res.data)
}

/**
 * 获取某一个审批流的业务明细(新增一个操作-创建optCode)
 * @param id
 */
export function newOptCode() {
  return api.get('workflow/flow/opt/newOptCode').then(res => res.data)
}

/**
 * 获取某一个审批流的业务明细(新增一个操作)
 * @param id /flow/opt/page 保存一个， /flow/opt/pages 保存多个
 */
export function addOptPage(params) {
  return apiJson.post('workflow/flow/opt/page', params).then(res => res.data)
}

/**
 * 获取某一个审批流的业务明细(删除一个操作)
 * @param id
 */
export function removeOptPage(id) {
  return api.delete(`workflow/flow/opt/page/${id}`).then(res => res.data)
}

/**
 * 获取一个数据带子表，主键作为参数以key-value形式提交
 */
export function getFormaccess(params = {}) {
  return apiForm.get(`formaccess/${params.modelId}/new`).then(res => res.data)
}
