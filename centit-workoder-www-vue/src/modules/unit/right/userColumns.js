/**
 * Created by lu_sn on 2018/12/12.
 */
export default [
  {
    title: '用户名',
    key: 'userName',
    minWidth: 110,
  },
  {
    title: '机构名',
    key: 'unitName',
    minWidth: 110,
  },
  {
    title: '岗位',
    key: 'userStationText',
    minWidth: 110,
  },
  {
    title: '职务',
    key: 'userRankText',
    minWidth: 110,
  },
  {
    title: '是否主机构',
    key: 'isPrimary',
    align: 'center',
    minWidth: 120,
    format({row, value}) {
      return row.isPrimary === 'T' ? '是' : '否'
    },
  },
]
