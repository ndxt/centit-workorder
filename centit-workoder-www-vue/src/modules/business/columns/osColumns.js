export default [
  {
    title: '业务系统ID',
    key: 'osId',
    sortable: 'custom',
    minWidth: 168,
  },
  {
    title: '业务系统名称',
    key: 'osName',
    sortable: 'custom',
    minWidth: 168,
  },
  {
    title: '业务系统URL',
    key: 'osUrl',
    sortable: 'custom',
    minWidth: 168,
  },
  // {
  //   title: '数据同步接口URL',
  //   key: 'ddeSyncUrl',
  //   sortable: 'custom',
  //   minWidth: 168,
  // },
  // {
  //   title: '推送数据选项',
  //   key: 'sysDataPushOption',
  //   sortable: 'custom',
  //   minWidth: 168,
  // },
  {
    title: '更新时间',
    key: 'lastModifyDate',
    sortable: 'custom',
    minWidth: 168,
  },
  {
    title: '创建人员',
    key: 'created',
    sortable: 'custom',
    minWidth: 168,
    format({row, value}) {
      return row.createUserName || value
    },
  },
]
