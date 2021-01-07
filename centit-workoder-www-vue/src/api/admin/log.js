import {apiFactory} from '../index'

const api = apiFactory('admin')

/**
 * 查询日志列表
 *
 * @param params
 * @param sort
 * @param page
 * @returns {*}
 */
export function queryLog({params = {}, sort = {}, page = {}} = {}) {
  return api.get('/system/optlog', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 获取log
 * @param id
 * @returns {*}
 */
export function getLog(id) {
  return api.get(`/system/optlog/${id}`)
    .then(res => res.data)
}
