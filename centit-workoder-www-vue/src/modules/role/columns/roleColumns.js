export default [
  {
    title: '编码',
    key: 'roleCode',
    sortable: 'custom',
    minWidth: 110,
  },
  {
    title: '名称',
    key: 'roleName',
    sortable: 'custom',
    minWidth: 110,
  },
  {
    title: '创建时间',
    key: 'createDate',
    align: 'center',
    sortable: 'custom',
    minWidth: 110,
  },
  {
    title: '类型',
    key: 'roleType',
    align: 'center',
    sortable: 'custom',
    minWidth: 110,
    format({row, value}) {
      return row.roleTypeText || value
    },
  },
  {
    title: '描述',
    key: 'roleDesc',
    minWidth: 200,
  },
]
