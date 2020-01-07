export default [
  {
    title: '创建时间',
    key: 'optTime',
    sortable: 'custom',
    minWidth: 168,
  },
  {
    title: '日志级别',
    key: 'logLevel',
    sortable: 'custom',
    minWidth: 120,
    format({value}) {
      return value === '0' ? '操作日志' : '错误提示'
    },
  },
  {
    title: '操作用户',
    key: 'userCode',
    sortable: 'custom',
    minWidth: 120,
    format({row, value}) {
      return row['userName'] || value
    },
  },
  {
    title: '操作方法',
    key: 'optMethod',
    sortable: 'custom',
    minWidth: 120,
    format({row, value}) {
      return row['optMethodText'] || value
    },
  },
  {
    title: '操作模块',
    key: 'optId',
    sortable: 'custom',
    minWidth: 168,
    format({row, value}) {
      return row['optId'] || value
    },
  },
]
