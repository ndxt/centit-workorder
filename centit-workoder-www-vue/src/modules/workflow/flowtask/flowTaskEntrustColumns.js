export default [
  {
    title: '委托人',
    key: 'grantor',
    sortable: 'custom',
  },
  {
    title: '部门',
    key: 'unitCode',
    sortable: 'custom',
  },
  {
    title: '角色',
    key: 'roleCode',
    sortable: 'custom',
    format({row, value}) {
      return value.join(',') || value
    },
  },
  {
    title: '被委托人',
    key: 'grantee',
    sortable: 'custom',
  },
  {
    title: '开始时间',
    key: 'relegateTime',
    sortable: 'custom',
  },
  {
    title: '结束时间',
    key: 'expireTime',
    sortable: 'custom',
  },
  {
    title: '描述',
    key: 'grantDesc',
    sortable: 'custom',
  },
]
