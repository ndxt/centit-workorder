export default [
  {
    title: '业务名称',
    key: 'flowOptName',
    sortable: 'custom',
  },
  {
    title: '业务号',
    key: 'flowOptTag',
    sortable: 'custom',
  },
  {
    title: '流程代码',
    key: 'flowCode',
    sortable: 'custom',
  },
  {
    title: '用户',
    key: 'userCode',
    sortable: 'custom',
    format({row, value}) {
      return row['userName'] || value
    },
  },
  {
    title: '机构',
    key: 'unitCode',
    sortable: 'custom',
    format({row, value}) {
      return row['unitName'] || value
    },
  },
  {
    title: '节点名称',
    key: 'nodeName',
    sortable: 'custom',
  },
  {
    title: '创建时间',
    key: 'createTime',
    align: 'center',
    sortable: 'custom',
  },
]
