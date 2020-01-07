const PageFlowBusiness = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/workflow/flowbusiness/PageFlowBusiness')
const PageFlowDefine = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/workflow/flowdefine/PageFlowDefine')
const PageFlowTask = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/workflow/flowtask/PageFlowTask')
const PageFlowManager = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/workflow/flowmanager/PageFlowManager')
const PageFlowFormula = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/workflow/flowformula/PageFlowFormula')

const PageApprovalBusiness = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/workflow/flowapprovalbusiness/PageApprovalBusiness')
const PageFlowStuffManager = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/workflow/flowstuffmanager/PageFlowStuffManager')
const PageFlowDocumentManager = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/workflow/flowdocumentmanager/PageFlowDocumentManager')
const PageApprovalModule = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/workflow/flowapprovalmodule/PageApprovalModule')
const PageFlowApprovalView = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/workflow/flowapprovalview/PageFlowApprovalView')
const ModuleInterfaceConfig = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/workflow/moduleinterfacedefine/ModuleInterfaceConfig')
const PageFlowDocument = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/workflow/flowdocument/PageFlowDocument')
const PageFlowRole = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/workflow/flowroledefine/PageFlowRoleDefine')

export default [
  {
    path: '/PageFlowOperatorDefine',
    name: 'WORKYW',
    component: PageFlowBusiness,
    meta: {
      title: '流程业务',
    },
  },

  {
    path: '/PageFlowDefine',
    name: 'WFDEFINE',
    component: PageFlowDefine,
    meta: {
      title: '流程定义',
    },
  },

  {
    path: '/PageTaskDelegate',
    name: 'FLOWTASK',
    component: PageFlowTask,
    meta: {
      title: '任务管理',
    },
  },

  {
    path: '/PageFlowManager',
    name: 'FLOWMGR',
    component: PageFlowManager,
    meta: {
      title: '流程实例管理',
    },
  },

  {
    path: '/PageFlowFormula',
    name: 'FLOWFORMULA',
    component: PageFlowFormula,
    meta: {
      title: '权限表达式资源库维护',
    },
  },

  {
    path: '/PageFlowRole',
    name: 'FLOWROLE',
    component: PageFlowRole,
    meta: {
      title: '审批角色',
    },
  },

  // 审批流

  {
    path: '/PageBusinessForm',
    name: 'FORMDEF',
    component: PageApprovalBusiness,
    meta: {
      title: '业务表单',
    },
  },

  {
    path: '/PageFlowStuffManager',
    name: 'STUFFMGR',
    component: PageFlowStuffManager,
    meta: {
      title: '材料维护',
    },
  },

  {
    path: '/PageFlowDocumentManager',
    name: 'DOCUMENTMGR',
    component: PageFlowDocumentManager,
    meta: {
      title: '文档维护',
    },
  },

  {
    path: '/PageGeneralModule',
    name: 'GENERALMODULE',
    component: PageApprovalModule,
    meta: {
      title: '通用审批模块',
    },
  },

  {
    path: '/ModuleInterfaceConfig',
    name: 'FLOWMODULE',
    component: ModuleInterfaceConfig,
    meta: {
      title: '模块接口配置',
    },
  },

  // newpage
  {
    path: '/MaterialModule',
    name: 'MaterialModule',
    component: PageFlowStuffManager,
    meta: {
      title: '材料组维护',
    },
  },

  {
    path: '/DocModule',
    name: 'DocModule',
    component: PageFlowDocument,
    meta: {
      title: '公文模板维护',
    },
  },

  {
    path: '/ApprovalModule',
    name: 'ApprovalModule',
    component: PageApprovalModule,
    meta: {
      title: '通用审批模块',
    },
  },

  {
    path: '/ViewApprovalShow',
    name: 'ViewApprovalShow',
    component: PageFlowApprovalView,
    meta: {
      title: '审批流实例查看',
    },
  },
]
