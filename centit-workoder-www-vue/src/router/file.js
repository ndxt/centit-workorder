const PageFileAccess = () => import('@/modules/file/PageFileAccess')
const PageFileManage = () => import('@/modules/file/PageFileManage')
const PageFileLocal = () => import('@/modules/file/PageFileLocal')

export default [
  {
    path: '/file/access',
    name: 'file_access',
    component: PageFileAccess,
    meta: {
      title: '文件授权日志',
    },
  },

  {
    path: '/file/manage',
    name: 'file_manage',
    component: PageFileManage,
    meta: {
      title: '文件管理',
    },
  },

  {
    path: '/file/local',
    name: 'file_local',
    component: PageFileLocal,
    meta: {
      title: '本地文件',
    },
  },
]
