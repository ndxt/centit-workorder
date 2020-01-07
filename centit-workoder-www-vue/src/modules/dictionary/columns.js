export default [
  {
    title: '字典编码',
    key: 'catalogCode',
    sortable: 'custom',
    minWidth: 168,
  },
  {
    title: '字典名称',
    key: 'catalogName',
    sortable: 'custom',
    minWidth: 168,
  },
  {
    title: '字典类型',
    key: 'catalogStyle',
    sortable: 'custom',
    minWidth: 120,
    format({row, value}) {
      return row['catalogStyleText'] || value
    },
  },
  {
    title: '字典结构',
    key: 'catalogType',
    sortable: 'custom',
    minWidth: 120,
    format({row, value}) {
      return row['catalogTypeText'] || value
    },
  },
  {
    title: '业务模块',
    key: 'optId',
    sortable: 'custom',
    minWidth: 168,
    format({row, value}) {
      return row['optName'] || value
    },
  },
]
