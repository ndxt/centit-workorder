const instStateText = {
  // A 草稿  E 已发布 (A,E仅对0版本有效) B 正常 C 过期 D 禁用
  N: '正常',
  C: '完成',
  P: '暂停',
  F: '强行结束',
  M: '已审批未完成',
}

export default [
  {
    title: '流程实例号',
    key: 'flowInstId',
    sortable: 'custom',
  },
  {
    title: '实例名称',
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
  },
  {
    title: '用户代码',
    key: 'userCode',
    format({row, value}) {
      return row['userName'] || value
    },
  },
  {
    title: '组织机构代码',
    key: 'unitCode',
    format({row, value}) {
      return row['unitName'] || value
    },
  },
  {
    title: '流程状态',
    key: 'instState',
    format({row, value}) {
      return instStateText[value] || value
    },
  },
  {
    title: '创建时间',
    key: 'createTime',
  },
  {
    title: '剩余时间',
    key: 'timeLimitStr',
  },
]
