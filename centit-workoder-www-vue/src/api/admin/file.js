import $ from 'jquery'

// 文件
import { apiFactory, createUrlParams } from '../index'

const api = apiFactory('file')

export function getFileRange(token, size, params) {
  return api.get('fileserver/upload/range', {
    params: {
      token,
      size,
      ...params
    }
  }).then(res => res.data)
}

export function uploadFileRange(range, file, params, {onUploadProgress} = {}) {
  const size = file.size
  const begin = range.begin
  const end = range.end

  return $.ajax(createUrlParams('api/file/fileserver/upload/range', {
    token: file.token,
    name: file.name,
    size,
    ...params,
  }), {
    data: range.data,
    contentType: false,
    processData: false,
    dataType: 'json',
    type: 'POST',
    mimeType: 'application/octet-stream',
    xhr: function() {
      const xhr = new XMLHttpRequest();
      // 使用XMLHttpRequest.upload监听上传过程，注册progress事件，打印回调函数中的event事件
      xhr.upload.addEventListener('progress', onUploadProgress)
      return xhr;
    },
    headers: {
      'Content-Type': 'application/octet-stream',
      'Content-Range': `bytes ${begin}-${end}/${size}`,
    },
  })

  // return api.post('fileserver/upload/range', range.data, {
  //   data: range.data,
  //
  //   params: {
  //     token: file.token,
  //     name: file.name,
  //     size,
  //     ...params,
  //   },
  //
  //   onUploadProgress,
  //
  //   headers: {
  //     'Content-Type': 'application/octet-stream',
  //     'Content-Range': `bytes ${begin}-${end}/${size}`,
  //   },
  // }).then(res => res.data)
}

export function uploadFile(file, params, {onUploadProgress} = {}) {
  return api.post('fileserver/upload/file', file, {
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

/**
 * 文件信息列表
 * @param params
 */
export function queryInfo({params = {}} = {}) {
  return api.get(`/fileserver/files/${params.fileId}`)
    .then(res => {
      const result = [
        {name: '文件名', feld: 'fileName'},
        {name: '创建时间', feld: 'createTime'},
        {name: '下载次数', feld: 'downloadTimes'},
        {name: '加密类型', feld: 'encryptType'},
        {name: '文件ID', feld: 'fileId'},
        {name: 'MD5', feld: 'fileMd5'},
        {name: '文件大小', feld: 'fileSize'},
        {name: '文件状态', feld: 'fileState'},
        {name: '文件类型', feld: 'fileType'},
        {name: '文件机构', feld: 'fileUnit'},
        {name: '索引状态', feld: 'indexState'},
        {name: '系统ID', feld: 'optId'},
        {name: '操作类别', feld: 'osId'},
      ]

      result.forEach(v => {
        v.value = res.data[v.feld]
      })

      return result
    })
}

/**
 * 下载日志列表
 * @param params
 */
export function queryLog({params = {}} = {}) {
  return api.get(`/fileserver/access/list/${params.fileId}`)
    .then(res => res.data)
}

/**
 * 文件授权日志列表
 * @param params
 * @param sort
 * @param page
 */
export function queryAccess({params = {}, sort = {}, page = {}} = {}) {
  return api.get('/fileserver/access/list', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 文件管理列表
 */
export function queryManage() {
  return api.get('/fileserver/files/oss')
    .then(res => res.data)
}

/**
 * 本地文件根目录列表
 */
export function queryLocal() {
  return api.get('fileserver/local/catalog')
    .then(res => {
      res.data.forEach(v => {
        v.accessToken = v.unitCode
        v.fileName = v.unitName
        v.root = `${v.unitCode}/`
      })
      res.data.unshift({
        accessToken: 'person',
        fileName: '个人文件',
        root: '',
      })
      return res.data
    })
}

/**
 * 本地文件个人下级列表
 * @param params
 */
export function queryUserLocal({params = {}} = {}) {
  return api.get(`/fileserver/local/userdir/${params.path || ''}`)
    .then(res => {
      res.data.filter(d => d.fileType === 'd')
        .forEach((v, i) => {
          v.accessToken = i
        })
      return res.data
    })
}

/**
 * 本地文件部门下级列表
 * @param params
 */
export function queryDeptLocal({params = {}} = {}) {
  return api.get(`/fileserver/local/unitdir/${params.path || ''}`)
    .then(res => {
      res.data.filter(d => d.fileType === 'd')
        .forEach((v, i) => {
          v.accessToken = i
        })
      return res.data
    })
}

/**
 * 本地文件个人下级版本列表
 * @param params
 */
export function queryUserVerLocal({params = {}} = {}) {
  return api.get(`/fileserver/local/userfile/${params.path || ''}`)
    .then(res => res.data)
}

/**
 * 本地文件部门下级版本列表
 * @param params
 */
export function queryDeptVerLocal({params = {}} = {}) {
  return api.get(`/fileserver/local/unitfile/${params.path || ''}`)
    .then(res => res.data)
}
