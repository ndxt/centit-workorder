const PageDynamic = () => import('@/modules/task/dynamic/PageDynamic')
const PageTransact = () => import('@/modules/task/transact/PageTransact')
const PageAttention = () => import('@/modules/task/attention/PageAttention')
const PureTransactHandle = () => import('@/modules/task/transact/PureTransactHandle')
const PureTransactView = () => import('@/modules/task/transact/PureTransactView')
const PureFlowInstView = () => import('@/modules/task/transact/operators/PureFlowInstView')
const PageDemo = () => import('@/modules/task/demo/PageDemo')

export default [
  {
    path: '/task/dynamic',
    name: 'DTDB',
    component: PageDynamic,
    meta: {
      title: '动态待办',
    },
  },

  {
    path: '/task/transact',
    name: 'USERTASKS',
    component: PageTransact,
    meta: {
      title: '我的待办',
    },
  },

  {
    path: '/task/attention',
    name: 'ATTENTIONS',
    component: PageAttention,
    meta: {
      title: '我的关注',
    },
  },

  {
    path: '/task/apprDemo',
    name: 'APPRDEMO',
    component: PageDemo,
    meta: {
      title: 'demo',
    },
  },

  {
    path: '/task/pure_transact/:id/:userCode',
    name: 'task_pureTransact',
    component: PureTransactHandle,
    meta: {
      title: '待办',
      fullscreen: true,
      needLogin: false,
    },
  },
  {
    path: '/task/pure_tranview/:id',
    name: 'task_pureTranView',
    component: PureTransactView,
    meta: {
      title: '已办',
      fullscreen: true,
      needLogin: false,
    },
  },
  {
    path: '/task/flowView/:id',
    name: 'task_flowView',
    component: PureFlowInstView,
    meta: {
      title: '已办',
      fullscreen: true,
      needLogin: false,
    },
  },
]
