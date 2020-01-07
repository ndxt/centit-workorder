const PageDatabase = () => import(/* webpackChunkName: "page-business" */ '@/modules/business/PageDatabase')
const PageOs = () => import(/* webpackChunkName: "page-business" */ '@/modules/business/PageOs')

export default [
  {
    path: '/PageDatabase',
    name: 'PageDatabase',
    component: PageDatabase,
    meta: {
      title: '集成数据库',
    },
  },

  {
    path: '/PageOs',
    name: 'PageOs',
    component: PageOs,
    meta: {
      title: '集成业务系统',
    },
  },
]
