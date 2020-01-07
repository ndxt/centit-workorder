import { apiFactory } from './index'

const api = apiFactory('admin')
// const match = {
//   SYS_CONFIG: {icon: 'ios-construct'},
//   USERINFO: {id: 'user'},
//   UNITINFO: {id: 'unit'},
//   ROLEMAG: {id: 'role'},
//   OPTINFO: {id: 'menu'},
//   DICTSET_M: {id: 'admin_dictionary'},
//   OPT_LOG_QUERY: {id: 'log'},
//   OPTLOG: {id: 'log'},
//
//   DEPTMAG: {icon: 'ios-contacts'},
//   DEPTUSERINFO: {id: 'department_user'},
//   DEPTPOW: {id: 'department_unit'},
//   DEPTROLE: {id: 'department_role'},
//
//   folder: {icon: 'ios-folder'},
//   file_access: {id: 'file_access'},
//   file_manage: {id: 'file_manage'},
//   file_local: {id: 'file_local'},
//
//   aperture: {icon: 'ios-aperture'},
//   DATABASE: {id: 'PageDatabase'},
//   OS_INFO: {id: 'PageOs'},
//
//   network: {icon: 'md-git-network'},
//   PageFlowBusiness: {id: 'WORKYW'},
//   PageFlowDefine: {id: 'WFDEFINE'},
//   PageFlowManager: {id: 'FLOWMGR'},
//   PageApprovalModule: {id: 'GENERALMODULE'},
//   PageBusinessForm: {id: 'FORMDEF'},
//
//   briefcase: {icon: 'ios-briefcase'},
//
//   clipboard: {icon: 'md-clipboard'},
//   task_transact: {id: 'task_transact'},
//
//   appstore: {icon: 'ios-appstore'},
//   FormWorld: {id: 'FormWorld'},
//   FileDemo: {id: 'FileDemo'},
//
//   DEPLOY: {icon: 'ios-appstore'},
//   DICTSET: {id: 'dictionary'},
//
//   ORGMAG: {icon: 'ios-contacts'},
//   USERMAG: {id: 'user'},
//   USERUNIT: {id: 'unit'},
//   USERROLE: {id: 'role'},
// }

export function queryMenus() {
  return api.get('/system/mainframe/menu')
    .then(res => res.data)
}

export function querySubMenus(id) {
  if (!id) {
    return queryMenus()
  }

  return api.get(`/system/mainframe/submenu?optid=${id}`)
    .then(res => res.data)
}

// function conversion(data) {
//   data.forEach(v => {
//     if (match[v.id]) {
//       for (const key in match[v.id]) {
//         v[key] = match[v.id][key]
//       }
//     }
//
//     v.name = v.text
//
//     if (v.children && v.children.length) {
//       conversion(v.children)
//     }
//   })
//
//   return data
// }
