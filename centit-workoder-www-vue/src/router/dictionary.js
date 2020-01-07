const PageDictionary = () => import(/* webpackChunkName: "page-dictionary" */ '@/modules/dictionary/PageDictionary')

export default [
  {
    path: '/dictionary',
    name: 'dictionary',
    component: PageDictionary,
    meta: {
      title: '数据字典',
    },
  },

  {
    path: '/admin/dictionary',
    name: 'admin_dictionary',
    component: PageDictionary,
    meta: {
      title: '数据字典(管理员)',
    },
    props: {
      admin: true,
    }
  },
]
