const PageRole = () => import(/* webpackChunkName: "page-role" */ '@/modules/role/PageRole')

export default [
  {
    path: '/role',
    name: 'role',
    component: PageRole,
    meta: {
      title: '角色管理',
    },
  },

  {
    path: '/department/role',
    name: 'department_role',
    component: PageRole,
    meta: {
      title: '角色定义(部门)',
    },
    props: {
      dept: true,
    }
  },
]
