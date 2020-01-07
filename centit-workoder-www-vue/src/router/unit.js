const PageUnit = () => import(/* webpackChunkName: "page-unit" */ '@/modules/unit/PageUnit')

export default [
  {
    path: '/unit',
    name: 'unit',
    component: PageUnit,
    meta: {
      title: '机构管理',
    },
  },

  {
    path: '/department/unit',
    name: 'department_unit',
    component: PageUnit,
    meta: {
      title: '机构管理(部门)',
    },
    props: {
      dept: true
    }
  },
]
