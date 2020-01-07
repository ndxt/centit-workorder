export default [
  {
    title: '用户编码',
    key: 'userCode',
    sortable: 'custom',
    align: 'center',
    minWidth: 150,
  },
  {
    title: '用户名',
    key: 'userName',
    sortable: 'custom',
    minWidth: 168,
  },
  {
    title: '登陆名',
    key: 'loginName',
    sortable: 'custom',
    minWidth: 168,
  },
  {
    title: '主机构名',
    key: 'primaryUnitName',
    sortable: 'custom',
    minWidth: 168,
    format({row, value}) {
      return row.primaryUnitName || value
    },
  },
]
