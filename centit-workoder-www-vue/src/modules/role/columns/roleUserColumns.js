export default [
  {
    title: '用户名',
    key: 'userName',
    minWidth: 110,
  },
  {
    title: '登录名',
    key: 'loginName',
    minWidth: 110,
  },
  {
    title: '主机构',
    key: 'userPrimaryUnit',
    minWidth: 110,
    format({row, value}) {
      return row.userPrimaryUnitText || value
    },
  },
  {
    title: '生效日期',
    key: 'obtainDate',
    align: 'center',
    minWidth: 110,
  },
  {
    title: '失效日期',
    key: 'secedeDate',
    align: 'center',
    minWidth: 110,
  },
  {
    title: '备注',
    key: 'changeDesc',
    minWidth: 200,
  },
]
