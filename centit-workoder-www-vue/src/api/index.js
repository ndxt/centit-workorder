import axios from 'axios'
import {goLogin} from '@/api/login'
import store from '../store'

/**
 * 南大先腾特有的Response结构
 * @param res
 * @returns {*}
 */
export const defaultTransformResponse = function (res) {
  if (typeof res === 'string') {
    try {
      res = JSON.parse(res)
    } catch (error) {

    }
  }
  if (!res) return res

  const {data, code, message} = res

  if (code && code !== 0) {
    throw new Error(message)
  }

  return data || res
}

/**
 * 将数据转换为表单数据
 * @param data
 * @returns {string}
 */
export const formDataTransformRequest = function (data) {
  // console.log(data, param(data))
  return param(data)
}

window.param = param
function param(data, result = '', identify = '') {
  function isValue(value) {
    if (value === null || value === undefined) return true

    if (value instanceof Date) return true

    return ['string', 'number', 'boolean'].includes(typeof value)
  }

  function isArray(value) {
    return value instanceof Array
  }

  /**
   * 编码数据
   * @param result
   * @param identify
   * @param value
   * @returns {*}
   */
  function encodeParam(result, identify, value = '') {
    if (!identify) {
      return result || value
    }

    value = encodeURIComponent(value)

    return result ? `${result}&${identify}=${value}` : `${identify}=${value}`
  }

  if (isValue(data) || isArray(data)) {
    return encodeParam(result, identify, data)
  }

  const keys = Object.keys(data)
  for (let key of keys) {
    const value = data[key]
    key = identify ? `${identify}.${key}` : key

    if (isValue(value)) {
      result = encodeParam(result, key, value)
    } else if (isArray(value)) {
      // if (value.length === 0) {
      //   result = encodeParam(result, key, '[]')
      // }

      value.forEach((v, i) => {
        result = param(v, result, `${key}[${i}]`)
      })
    } else {
      result = param(value, result, key)
    }
  }

  return result
}
window.$param = param

/**
 * 转换url参数
 * @deprecated GET 请求时使用 axios 自带转换参数
 * @param url
 * @param params
 * @returns {string}
 */
export function createUrlParams(url, params) {
  const hasWen = url.indexOf('?') > -1 ? '' : '?'
  const result = []

  for (const key of Object.keys(params)) {
    const value = params[key]
    if (value !== undefined) {
      result.push(`${key}=${value}`)
    }
  }

  return result.length ? `${url}${hasWen}${result.join('&')}` : url
}

/**
 * 添加搜索前缀
 * @param params
 * @param prefix
 */
export function addSearchParamsPrefix(params = {}, prefix = '') {
  const result = {}
  for (let key of Object.keys(params)) {
    result[`${prefix}${key}`] = params[key]
  }

  return result
}

const apiMap = {}

export function apiFactory(serverName, options = {}, json) {
  // if (apiMap[serverName]) return apiMap[serverName]

  const api = axios.create({
    baseURL: `api/${serverName}`,
    responseType: 'json',
    headers: {
      'X-Requested-With': 'XMLHttpRequest',
      'Content-Type': json ? 'application/json; charset=UTF-8' : 'application/x-www-form-urlencoded; charset=UTF-8',
    },
    transformRequest: json ? [] : [formDataTransformRequest],
    transformResponse: [defaultTransformResponse],
  })

  api.interceptors.request.use((config) => {
    const {url} = config
    if (url && url === '/login') {
      return config
    }

    // 添加token
    const token = store.state.token || localStorage.accessToken
    if (token) {
      config.headers['x-auth-token'] = token
    }

    return config
  })

  api.interceptors.response.use((response) => {
    // 获取token并保存
    // const {headers, request} = response
    // const token = headers['x-auth-token']
    // if (token) {
    //   console.log(request.responseURL, token)
    //   store.commit('setToken', token)
    // }

    // Login.vue 已经存过token

    return response
  }, (error) => {
    if (error.request && error.response === undefined) {
      goLogin()
    } else {
      // Unauthorized
      if (error.response) {
        const {status} = error.response

        if ([401, 403].includes(status)) {
          goLogin()
        }
      }
    }

    return Promise.reject(error.message)
  })

  apiMap[serverName] = api

  return api
}
