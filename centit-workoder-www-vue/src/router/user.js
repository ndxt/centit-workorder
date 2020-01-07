const PageUser = () => import(/* webpackChunkName: "page-user" */ '@/modules/user/PageUser')

export default [
  {
    path: '/user',
    name: 'user',
    component: PageUser,
    meta: {
      title: '用户管理',
    },
  },

  {
    path: '/department/user',
    name: 'department_user',
    component: PageUser,
    meta: {
      title: '用户管理(部门)',
    },
    props: {
      dept: true,
    },
  },
]
