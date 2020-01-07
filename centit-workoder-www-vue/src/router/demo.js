const FormWorld = () => import(/* webpackChunkName: "page-form-world" */ '@/components/FormWorld')
const FileDemo = () => import(/* webpackChunkName: "page-file-world" */ '@/components/FileDemo')

export default [
  {
    path: '/form',
    name: 'FormWorld',
    component: FormWorld,
    meta: {
      title: '表单示例',
    },
  },

  {
    path: '/file',
    name: 'FileDemo',
    component: FileDemo,
    meta: {
      title: '文件上传',
    },
  },
]
