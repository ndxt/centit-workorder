
import {
  apiFactory,
} from './index'

const api = apiFactory('workorder', {}, true)
export function addOrder(data) {
  return api.post(`/workorder/os/${data.osId}/catalogs`, data)
    .then(res => res.data)
}

export function getRankList(data) {
  return api.get(`/workorder/os/${data.osId}/catalogs`)
    .then(res => res.data)
}
