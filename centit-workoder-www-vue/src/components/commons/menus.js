export default [
  {
    name: '系统维护',

    icon: 'ios-construct',

    children: [
      {id: 'user', name: '用户管理'},

      {id: 'unit', name: '机构管理'},

      {id: 'role', name: '角色管理'},

      {id: 'menu', name: '菜单管理'},

      {id: 'dictionary', name: '数据字典'},

      {id: 'admin_dictionary', name: '数据字典(管理员)'},

      {id: 'log', name: '日志管理'},
    ],
  },

  {
    name: '贵州申报演示',

    icon: 'logo-apple',

    children: [
      {id: 'gz_chart', name: '统计图表', path: '/meta/chart/5ea3deb53663456787d85e0f7587ea58'},

      {id: 'gz_report', name: '统计报表', path: '/meta/report/59dded91346448268924fd58b3677b6b'},

      {id: 'gz_list', name: '申报列表', path: '/meta/model/0HREmJk_SV-RWhF_RsGhhg'},

      {id: 'sx_zyjjzb', name: '陕西主要经济指标', path: '/sx/zyjjzb'},
      {id: 'sx_qgzyzbdb', name: '全国主要指标对比', path: '/sx/qgzyzbdb'},
      {id: 'sx_gszyjjzb', name: '各市主要经济指标', path: '/sx/gszyjjzb'},
      {id: 'sx_qgjjzbpm', name: '全国经济指标排名', path: '/sx/qgjjzbpm'},
    ],
  },

  {
    name: '陕西大屏演示',

    icon: 'logo-apple',

    children: [
      {id: 'index', name: '首页'},
    ],
  },

  {
    name: '部门管理',

    icon: 'ios-contacts',

    children: [

      {id: 'department_user', name: '用户管理(部门)'},

      {id: 'department_unit', name: '机构管理(部门)'},

      {id: 'department_role', name: '角色定义(部门)'},

    ],
  },

  {
    name: '文件管理',

    icon: 'ios-folder',

    children: [

      {id: 'file_access', name: '文件授权日志'},

      {id: 'file_manage', name: '文件管理'},

      {id: 'file_local', name: '本地文件'},

    ],
  },

  {
    name: '业务平台',

    icon: 'ios-aperture',

    children: [

      {id: 'PageDatabase', name: '集成数据库'},

      {id: 'PageOs', name: '集成业务系统'},

    ],
  },

  {
    name: '统计分析',

    icon: 'ios-stats',

    children: [

      {id: 'stats_base', name: '元数据'},
      {id: 'stats_mdtable', name: '表结构数据定义'},
      {id: 'stats_setCharts', name: '图表'},
      {id: 'stats_resource', name: '数据包'},
      {id: 'stats_dataexmg', name: '数据交换管理'},

    ],
  },

  {
    name: '工作流',

    icon: 'md-git-network',

    children: [

      {id: 'FLOWMODULE', name: '模块接口配置'},

      {id: 'WORKYW', name: '流程业务定义'},

      {id: 'WFDEFINE', name: '流程定义'},

      {id: 'FLOWMGR', name: '流程实例管理'},

      {id: 'GENERALMODULE', name: '通用审批模块'},

      {id: 'FORMDEF', name: '业务表单'},

    ],
  },

  {
    name: '业务管理',

    icon: 'ios-briefcase',

    children: [

      {id: 'BJRETRIEVE', name: '办件回收'},

      {id: 'TASKDELEGATE', name: '任务委托'},

    ],
  },

  {
    name: '我的任务',

    icon: 'md-clipboard',

    children: [

      {id: 'DTDB', name: '动态待办'},

      {id: 'USERTASKS', name: '我的待办'},

      {id: 'ATTENTIONS', name: '我的关注'},

    ],
  },

  {
    name: '示例',

    icon: 'ios-appstore',

    children: [

      {id: 'FormWorld', name: '表单'},

      {id: 'FileDemo', name: '文件'},

    ],
  },
]
