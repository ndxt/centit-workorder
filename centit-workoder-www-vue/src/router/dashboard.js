const Dashboard = () => import(/* webpackChunkName: "page-hello-world" */ '@/components/HelloWorld')

export default [
  {

    path: '/dashboard',
    name: 'dashboard',
    component: Dashboard,
    meta: {
      title: '首页',
    },
  },
]
