import {apiFactory} from '../index'

let api = apiFactory('workorder')

/**
 * 获取知识库树形数据
 */
export function knowedgeTreeData() {
  return api.get('/workorder/os/N002/documents/levelSearch').then(res => res.data.objList);
}

/**
 * 文档搜索
 */
export function knowedgeSeach(parms = {}) {
  return api.get(`/workorder/os/N002/documents/fullSearch/${parms.docName}?isAdmin=T`).then(res => res.data);
}

/**
 * 添加数节点
 */
export function addTreeDoc(parms = {}) {
  return apiFactory('workorder', {}, 'application/json;charset=UTF-8').post(`/workorder/os/N002/documents`, parms).then(res => res.data);
}

/**
 * 删除树节点
 */
export function deleteTreeDoc(parms = {}) {
  return api.delete(`/workorder/os/N002/documents/${parms.docId}`).then(res => res.data);
}

/**
 * 编辑树节点
 */
export function eidtTreeDoc(parms = {}) {
  console.log(parms)
  return apiFactory('workorder', {}, 'application/json;charset=UTF-8').put(`/workorder/os/N002/documents/${parms.docId}`, parms).then(res => res.data);
}

/**
 * 获取文档
 */
export function getDoc(parms = {}) {
  return api.get(`/workorder/os/N002/documents/${parms.docId}`).then(res => res.data);
}

/**
 * 编辑文档
 */
export function editDoc(parms = {}) {
  return apiFactory('workorder', {}, 'application/json;charset=UTF-8').put(`/workorder/os/N002/documents/${parms.docId}/content`, parms).then(res => res.data)
}
