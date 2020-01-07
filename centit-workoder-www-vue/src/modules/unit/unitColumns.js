
export default [
  {
    title: '名称',
    key: 'unitName',
    sortable: 'custom',
    minWidth: 150,
  },
  {
    title: '编号',
    key: 'unitCode',
    sortable: 'custom',
    minWidth: 110,
  },
  {
    title: '编码',
    key: 'unitWord',
    sortable: 'custom',
    minWidth: 110,
  },
  {
    title: '排序',
    key: 'unitOrder',
    sortable: 'custom',
    align: 'center',
    minWidth: 80,
  },
  // {
  //   title: '类别',
  //   key: 'unitType',
  //   align: 'center',
  //   minWidth: 110,
  //   format({row, value}) {
  //     return row.unitTypeText || value
  //   },
  // },
  {
    title: '描述',
    key: 'unitDesc',
    minWidth: 200,
  },
]
