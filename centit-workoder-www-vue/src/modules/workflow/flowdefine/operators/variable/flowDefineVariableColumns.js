const variableTypeText = {
  E: '集合',
  S: '单值',
}

export default [
  // {
  //   title: '变量ID',
  //   key: 'flowVariableId',
  //   sortable: 'custom',
  // },
  {
    title: '变量名称',
    key: 'variableName',
    sortable: 'custom',
  },
  {
    title: '中文描述',
    key: 'variableDesc',
    sortable: 'custom',
  },
  {
    title: '变量类型',
    key: 'variableType',
    sortable: 'custom',
    format({row, value}) {
      return variableTypeText[value] || value
    },
  },
  {
    title: '默认值',
    key: 'defaultValue',
    sortable: 'custom',
  },
  // {
  //   title: '显示顺序',
  //   key: 'variableOrder',
  //   sortable: 'custom',
  // },
]
