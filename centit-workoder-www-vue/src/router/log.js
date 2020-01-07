const Log = () => import(/* webpackChunkName: "page-menu" */ '@/modules/log/PageLog')

export default [
  {
    path: '/log',
    name: 'log',
    component: Log,
    meta: {
      title: '日志管理',
    },
  },
]
