const Login = () => import(/* webpackChunkName: "page-login" */ '@/modules/login/Login')

export default [
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {
      needLogin: false,
      fullscreen: true,
      title: '登录',
    },
  },

  {
    path: '/loginAsAdmin',
    name: 'loginAsAdmin',
    component: Login,
    props: {
      admin: true,
    },
    meta: {
      needLogin: false,
      fullscreen: true,
      title: '管理员登录',
    },
  },
]
