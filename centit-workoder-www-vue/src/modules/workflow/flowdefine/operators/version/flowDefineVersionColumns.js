const packetTypeText = {
  // A 草稿  E 已发布 (A,E仅对0版本有效) B 正常 C 过期 D 禁用
  A: '草稿',
  E: '已发布',
  B: '正常',
  C: '过期',
  D: '禁用',
}

export default [
  {
    title: '流程名称',
    key: 'flowName',
    sortable: 'custom',
  },
  {
    title: '版本',
    key: 'version',
    sortable: 'custom',
  },
  {
    title: '状态',
    key: 'flowState',
    sortable: 'custom',
    format({row, value}) {
      return packetTypeText[value] || value
    },
  },
]
