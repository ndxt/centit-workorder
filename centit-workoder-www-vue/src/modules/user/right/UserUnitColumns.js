export default [
  {
    title: '机构名',
    key: 'unitName',
    align: 'center',
    minWidth: 110,
  },
  {
    title: '岗位',
    key: 'userStation',
    align: 'center',
    minWidth: 110,
    format({row, value}) {
      return row.userStationText || value
    },
  },
  {
    title: '职务',
    key: 'userRank',
    align: 'center',
    minWidth: 110,
    format({row, value}) {
      return row.userRankText || value
    },
  },
  {
    title: '是否主机构',
    key: 'isPrimary',
    align: 'center',
    minWidth: 120,
    format({row, value}) {
      return row.isPrimary === 'F' ? '否' : '是'
    },
  },
]
