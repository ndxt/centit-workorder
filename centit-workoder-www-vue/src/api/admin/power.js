// 权限
import { apiFactory } from '../index'

const api = apiFactory('admin')

/**
 * 权限列表
 */
export async function queryPower() {
  return api.get('/system/optinfo/poweropts')
    .then(res => res.data)
}

/**
 * 权限范围列表
 * @param params
 */
export function queryPowerRange({params = {}} = {}) {
  return api.get(`/system/optinfo/${params.optId}`)
    .then(res => {
      if (!res.data || !res.data.dataScopes) return []

      res.data.dataScopes.forEach(v => {
        v._checked = false
      })
      return res.data.dataScopes
    })
}
