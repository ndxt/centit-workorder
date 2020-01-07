export default [
/*  {
    title: '数据编码',
    key: 'databaseCode',
    sortable: 'custom',
    minWidth: 168,
  }, */
  {
    title: '数据库名',
    key: 'databaseName',
    sortable: 'custom',
    minWidth: 168,
  },
  {
    title: '数据库连接URL',
    key: 'databaseUrl',
    sortable: 'custom',
    minWidth: 168,
  },
  {
    title: '用户名',
    key: 'username',
    sortable: 'custom',
    minWidth: 168,
  },
  {
    title: '业务系统ID',
    key: 'osId',
    sortable: 'custom',
    minWidth: 168,
  },
  {
    title: '创建时间',
    key: 'createTime',
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
