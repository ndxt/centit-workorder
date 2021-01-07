import Vue from 'vue'
import {apiFactory,} from './index'

const api = apiFactory('workorder', {}, true)
export function addOrder(data) {
  return api.post(`/workorder/os/${Vue.osId}/catalogs`, data)
    .then(res => res.data)
}

export function addSubOrder(data) {
  return api.post(`/workorder/os/${Vue.osId}/catalogs`, data)
    .then(res => res.data)
}
export function addQuestion(data) {
  return api.post(`/workorder/os/${Vue.osId}/questions`, data.questionInfo)
    .then(res => res.data)
}

export function editOuestion(data) {
  return api.put(`/workorder/os/${Vue.osId}/questions/${data.questionId}`, data.questionInfo).then(res => res.data)
}

export function assignOuestion(data) {
  return api.put(`/workorder/os/${Vue.osId}/questions/${data.questionId}/operator`, data.assignInfo).then(res => res.data)
}

export function searchQuestion({params}) {
  return api.get(`/workorder/os/${Vue.osId}/questions?${params.searchStr}`)
    .then(res => res.data)
}
export function removeQuestion(data) {
  return api.delete(`/workorder/os/${Vue.osId}/questions/${data.id}`).then(res => res.data)
}
export function getRankList({params}) {
  return api.get(`/workorder/os/${Vue.osId}/catalogs/rankList`)
    .then(res => res.data)
}

export function removeOrder(data) {
  return api.delete(`/workorder/os/${Vue.osId}/catalogs/${data.catalogId}`).then(res => res.data)
}
export function getOrder(data) {
  return api.get(`/workorder/os/${Vue.osId}/catalogs/${data.catalogId}`).then(res => res.data)
}
export function changeOrder(data) {
  return api.put(`/workorder/os/${Vue.osId}/catalogs/${data.catalogId}`, data).then(res => res.data)
}

export function docSearch(data) {
  return api.get(`/workorder/os/${Vue.osId}/documents/fullTextSearch/${data.catalogId}`, data).then(res => res.data)
}

export function getOsList() {
  return api.get(`/workorder/os`)
    .then(res => res.data)
}
