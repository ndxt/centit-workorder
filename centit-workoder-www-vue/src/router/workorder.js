const SubmitOrderAdmin = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/submitOrder/admin')
const SubmitOrderUser = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/submitOrder/user')
const QuestionUser = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/questions/user')
const QuestionAdmin = () => import(/* webpackChunkName: "page-workflow" */ '@/modules/questions/admin')
export default [
  {
    path: '/admin/SubmitOrder',
    name: 'AdminOrder',
    component: SubmitOrderAdmin,
    meta: {
      title: '流程业务',
    },
  },
  {
    path: '/user/SubmitOrder',
    name: 'UserOrder',
    component: SubmitOrderUser,
    meta: {
      title: '流程业务',
    },
  },
  {
    path: '/user/questions',
    name: 'UserQuestion',
    component: QuestionUser,
    meta: {
      title: '流程业务',
    },
  },
  {
    path: '/admin/questions',
    name: 'AdminQuestion',
    component: QuestionAdmin,
    meta: {
      title: '流程业务',
    },
  }
]
