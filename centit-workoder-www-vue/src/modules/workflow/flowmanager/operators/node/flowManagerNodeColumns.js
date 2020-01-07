export default [
  {
    title: '节点名称',
    key: 'nodeName',
  },
  {
    title: '状态',
    key: 'nodeState',
  },
  {
    title: '用户code',
    key: 'trainsUsers',
    format({row, value}) {
      return value.length ? value.join(',') : ''
    }
  },
  {
    title: '最后更新时间',
    key: 'lastUpdateTime',
  },
  {
    title: '期限',
    key: 'timeLimitStr',
  }
]
