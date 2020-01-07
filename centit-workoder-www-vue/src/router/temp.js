const TempChart = () => import(/* webpackChunkName: "gz_chart" */ '@/modules/temp/TempChart')
const TempReport = () => import(/* webpackChunkName: "gz_chart" */ '@/modules/temp/TempReport')
const TempModel = () => import(/* webpackChunkName: "gz_chart" */ '@/modules/temp/TempModel')
const HotTable = () => import(/* webpackChunkName: "gz_chart" */ '@/modules/temp/HotTable')

export default [
  {
    path: '/meta/chart/:id',
    name: 'TempChart',
    component: TempChart,
    meta: {
      title: '图表',
    },
  },

  {
    path: '/meta/report/:id',
    name: 'TempReport',
    component: TempReport,
    meta: {
      title: '报表',
    },
  },

  {
    path: '/meta/model/:id',
    name: 'TempModel',
    component: TempModel,
    meta: {
      title: '列表',
    },
  },

  {
    path: '/sx/zyjjzb',
    name: 'sx_zyjjzb',
    component: HotTable,
    meta: {
      title: '陕西主要经济指标',
    },
  },

  {
    path: '/sx/qgzyzbdb',
    name: 'sx_qgzyzbdb',
    component: HotTable,
    meta: {
      title: '全国主要指标对比',
    },
  },

  {
    path: '/sx/gszyjjzb',
    name: 'sx_gszyjjzb',
    component: HotTable,
    meta: {
      title: '各市主要经济指标',
    },
  },

  {
    path: '/sx/qgjjzbpm',
    name: 'sx_qgjjzbpm',
    component: HotTable,
    meta: {
      title: '全国经济指标排名',
    },
  },
]
