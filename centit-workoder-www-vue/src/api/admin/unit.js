// 机构管理
import { apiFactory } from '../index'

const api = apiFactory('admin')

function setState(data) {
  if (data.length) {
    data.forEach(v => {
      if (v.state === 'open' && v.children.length) {
        v.state = 'closed'
        setState(v.children)
      }
    })
  }
}

/**
 * 获取机构列表
 * @param params
 * @param sort
 * @param page
 */
export function queryUnit({params = {}, sort = {}, id} = {}) {
  return api.get('/system/unitinfo', {
    params: {
      ...params,
      ...sort,
      id,
    }
  }).then(res => res.data)
}

/**
 * 获取登录用户部门下子部门
 * @returns {*}
 */
export function queryDepartmentUnit() {
  return api.get('/system/unitinfo/validsubunits')
    .then(res => res.data)
}

/**
 * 获取下属机构列表
 * @param params
 * @param sort
 * @param page
 */
export function querySubUnit({params = {}, sort = {}, id} = {}) {
  return api.get('/system/unitinfo/subunits', {
    params: {
      ...params,
      ...sort,
      id,
    }
  }).then(res => {
    setState(res.data)
    return res.data
  })
}

/**
 * 判断新增时编码是否唯一
 * @param params
 */
export function checkUnitWordUnique(params = {}) {
  return api.get('/system/unitinfo/unitWord_usability', {
    params: {
      unitWord: params
    }
  }).then(res => res.data)
}

/**
 * 判断编辑时编码是否唯一
 * @param params
 */
export function checkUnitUpdateWordUnique(params = {}) {
  return api.get(`/system/unitinfo/${params.unitCode}/unitWord_usability`, {
    params: {
      unitWord: params.unitWord
    }
  }).then(res => res.data)
}

/**
 * 获取单个机构信息
 * @param params
 */
export function getUnit(params = {}) {
  return api.get(`/system/unitinfo/${params.unitCode}`)
    .then(res => res.data)
}

/**
 * 保存单个机构信息
 * @param params
 */
export function addUnit(params = {}) {
  return api.post('/system/unitinfo', params)
    .then(res => res.data)
}

/**
 * 编辑单个机构信息
 * @param params
 */
export function saveUnit(params = {}) {
  return api.put(`/system/unitinfo/${params.unitCode}`, {
    ...params
  }).then(res => res.data)
}

/**
 * 删除机构
 * @param params
 */
export function removeUnit(params = {}) {
  return api.delete(`/system/unitinfo/${params.unitCode}`)
}

/**
 * 获取操作权限
 * @param params
 */
export function getUnitPower(params = {}) {
  return api.get(`/system/roleinfo/power/unit/${params.unitCode}`)
    .then(res => res.data)
}

/**
 * 更新操作权限
 * @param unitCode
 * @param authorities
 */
export function saveUnitPower(unitCode, authorities = []) {
  return api.post(`/system/unitinfo/${unitCode}/authorities`, {
    authorities
  }).then(res => res.data)
}

/**
 * 右侧获取机构人员列表
 * @param params
 * @param code
 */
export function queryUnitUser({params = {}, sort = {}, page = {}} = {}) {
  return api.get(`/system/userunit/unitusers/${params.id}`, {
    params: {
      ...params,
      ...sort,
      ...page,
    }
  }).then(res => res.data)
}

/**
 * 右侧新增机构人员
 * @param params
 * @param code
 */
export function addUnitUser(params = {}) {
  return api.post('/system/userunit', params)
    .then(res => res.data)
}

/**
 * 右侧修改机构人员
 * @param params
 * @param code
 */
export function editUnitUser(params = {}) {
  return api.put(`/system/userunit/${params.userUnitId}`, params)
    .then(res => res.data)
}

/**
 * 右侧删除机构人员
 * @param params
 * @param code
 */
export function removeUnitUser(id) {
  return api.delete(`/system/userunit/${id}`)
    .then(res => res.data)
}

/**
 * 右侧获取机构角色列表
 * @param params
 * @param code
 */
export function queryUnitRole({params = {}, sort = {}, page = {}} = {}) {
  return api.get(`/system/unitrole/unitroles/${params.id}`, {
    params: {
      ...sort,
      ...page,
    }
  }).then(res => res.data)
}

/**
 * 右侧获取下属机构的角色列表
 * @param params
 * @param code
 */
export function queryCurrentUnitRole({params = {}, sort = {}, page = {}} = {}) {
  return api.get(`/system/unitrole/currentunitroles/${params.id}`, {
    params: {
      ...sort,
      ...page,
    }
  }).then(res => res.data)
}

/**
 * 右侧新增机构人员
 * @param params
 * @param code
 */
export function addUnitRole(params = {}) {
  return api.post('/system/unitrole', params)
    .then(res => res.data)
}

/**
 * 右侧删除机构角色
 * @param params
 * @param code
 */
export function removeUnitRole(params) {
  return api.delete(`/system/unitrole/${params.roleCode}/${params.unitCode}`).then(res => res.data)
}
