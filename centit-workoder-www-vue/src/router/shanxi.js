const Index = () => import('@/modules/shanxi/Index')

export default [
  {
    path: '/shanxi',
    name: 'index',
    component: Index,
    meta: {
      title: '首页',
      fullscreen: true,
      needLogin: false,
    },
  },
]
