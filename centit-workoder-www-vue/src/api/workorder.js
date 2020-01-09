
import {
  apiFactory,
} from './index'

const api = apiFactory('workorder', {}, true)
export function addOrder(data) {
  return api.post(`/workorder/os/${data.osId}/catalogs`, data)
    .then(res => res.data)
}

export function addSubOrder(data) {
  return api.post(`/workorder/os/${data.osId}/catalogs`, data)
    .then(res => res.data)
}

export function getRankList({params}) {
  return api.get(`/workorder/os/${params.osId}/catalogs/rankList`)
    .then(res => res.data)
}

export function removeOrder(data) {
  return api.delete(`/workorder/os/${data.osId}/catalogs/${data.catalogId}`).then(res => res.data)
}

export function changeOrder(data) {
  return api.put(`/workorder/os/${data.osId}/catalogs/${data.catalogId}`, data).then(res => res.data)
}
