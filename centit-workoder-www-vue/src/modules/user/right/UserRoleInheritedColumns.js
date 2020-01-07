export default [
  {
    title: '角色编码',
    key: 'roleCode',
    minWidth: 110,
  },
  {
    title: '角色名称',
    key: 'roleName',
    minWidth: 110,
  },
  {
    title: '继承自',
    key: 'unitName',
    align: 'center',
    minWidth: 100,
    format({row, value}) {
      return row.unitName || value
    },
  },
  {
    title: '备注',
    key: 'roleDesc',
    minWidth: 200,
  },
]
