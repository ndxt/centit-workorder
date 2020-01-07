export default [
  {
    title: '用户名',
    key: 'userName',
    minWidth: 120,
  },
  {
    title: '继承自',
    key: 'unitName',
    minWidth: 200,
    format({row, value}) {
      return row.unitName || value
    },
  },
]
