const PageMenu = () => import(/* webpackChunkName: "page-menu" */ '@/modules/menu/PageMenu')

export default [
  {
    path: '/menu',
    name: 'menu',
    component: PageMenu,
    meta: {
      title: '菜单管理',
    },
  },
]
