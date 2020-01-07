// 角色管理
import { apiFactory } from '../index'

const api = apiFactory('admin')

/**
 * 角色列表
 * @param params
 * @param sort
 * @param page
 */
export function queryRole({ params = {}, sort = {}, page = {} } = {}) {
  return api.get('/system/roleinfo/all', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 角色列表（部门）
 * @param params
 * @param sort
 * @param page
 */
export function queryDeptRole({ params = {}, sort = {}, page = {} } = {}) {
  return api.get('/system/roleinfo/currentunit', {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 获取登录用户部门下角色
 * @returns {*}
 */
export function queryDepartmentRole() {
  return api.get('/system/roleinfo/listRoles/D')
    .then(res => res.data)
}

/**
 * 判断角色名称唯一
 * @param roleName
 */
export function checkRoleNameUnique(roleName) {
  if (!roleName) return true

  return api.get(`/system/roleinfo/issysroleunique/${roleName}`)
    .then(res => res.data)
}

/**
 * 获取角色
 * @param params
 */
export function getRole(params = {}) {
  return api.get(`/system/roleinfo/${params.roleCode}`)
    .then(res => res.data)
}

/**
 * 添加角色
 * @param params
 */
export function addRole(params = {}) {
  return api.post('/system/roleinfo', {
    ...params,
  }).then(res => res.data)
}

/**
 * 编辑角色
 * @param params
 */
export function saveRole(params = {}) {
  return api.put(`/system/roleinfo/${params.roleCode}`, {
    ...params,
  }).then(res => res.data)
}

/**
 * 删除角色
 * @param params
 */
export function removeRole(params = {}) {
  return api.delete(`/system/roleinfo/${params.roleCode}`)
    .then(res => res.data)
}

/**
 * 编辑角色权限
 * @param roleCode
 * @param rolePowers
 */
export function saveRolePower(roleCode, rolePowers = []) {
  return api.put(`/system/roleinfo/power/${roleCode}`, {
    rolePowers,
  }).then(res => res.data)
}

/**
 * 间接角色用户列表
 * @param params
 * @param sort
 * @param page
 */
export function queryRoleUserInherit({ params = {}, sort = {}, page = {} } = {}) {
  return api.get(`/system/userrole/roleusersinherited/${params.roleCode}`, {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 角色用户列表
 * @param params
 * @param sort
 * @param page
 */
export function queryRoleUser({ params = {}, sort = {}, page = {} } = {}) {
  return api.get(`/system/userrole/roleusers/${params.roleCode}`, {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 角色用户列表（部门）
 * @param params
 * @param sort
 * @param page
 */
export function queryDeptRoleUser({ params = {}, sort = {}, page = {} } = {}) {
  return api.get(`/system/userrole/rolecurrentusers/${params.roleCode}`, {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 添加角色用户
 * @param params
 */
export function addRoleUser(params = {}) {
  return api.post('/system/userrole', {
    ...params,
  }).then(res => res.data)
}

/**
 * 删除角色用户
 * @param params
 */
export function removeRoleUser(params = {}) {
  return api.delete(`/system/userrole/${params.roleCode}/${params.userCode}`)
    .then(res => res.data)
}

/**
 * 角色机构列表
 * @param params
 * @param sort
 * @param page
 */
export function queryRoleUnit({ params = {}, sort = {}, page = {} } = {}) {
  return api.get(`/system/unitrole/roleunits/${params.roleCode}`, {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 角色机构列表（部门）
 * @param params
 * @param sort
 * @param page
 */
export function queryDeptRoleUnit({ params = {}, sort = {}, page = {} } = {}) {
  return api.get(`/system/unitrole/rolesubunits/${params.roleCode}`, {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 添加角色机构
 * @param params
 */
export function addRoleUnit(params = {}) {
  return api.post('/system/unitrole', {
    ...params,
  }).then(res => res.data)
}

/**
 * 删除角色机构
 * @param params
 */
export function removeRoleUnit(params = {}) {
  return api.delete(`/system/unitrole/${params.roleCode}/${params.unitCode}`)
    .then(res => res.data)
}

/**
 * 查询子系统角色
 * @param params
 * @param sort
 * @param page
 */
export function querySubSysRole({ params = {}, sort = {}, page = {} } = {}) {
  return api.get(`/system/roleinfo/subSysRole/${params.topOptId}`, {
    params: {
      ...params,
      ...sort,
      ...page,
    },
  }).then(res => res.data)
}

/**
 * 创建子系统角色
 * @param params
 */
export function createSubSysRole(params = {}) {
  return api.post(`/system/roleinfo/subSysRole/${params.topOptId}`, {
    ...params,
  })
    .then(res => res.data)
}

/**
 * 创建子系统角色
 * @param params
 */
export function createDepartmentRole(params = {}) {
  return api.post(`/system/roleinfo/departmentRole`, {
    ...params,
  })
    .then(res => res.data)
}

/**
 * 创建子系统角色
 * @param params
 */
export function updateDepartmentRole(params = {}) {
  return api.put(`/system/roleinfo/departmentRole/${params.roleCode}`, {
    ...params,
  })
    .then(res => res.data)
}
