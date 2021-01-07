import $ from 'jQuery'

const USER_AVATAR = 'http://tva3.sinaimg.cn/crop.0.0.180.180.180/7f5f6861jw1e8qgp5bmzyj2050050aa8.jpg'

export const _getContextPath = () => {
  let match = location.href.match(/^(http:\/\/.*?\/.*?)\//)

  if (match && match[1]) {
    return match[1]
  }
}

/**
 * 工具函数：获取时间戳
 * @returns {number}
 * @private
 */
export const _getTimestamp = () => {
  return new Date().getTime()
}

export const _parsedata = (list) => {
  if (!list || !list.map) return list

  return list.map(d => $.extend(d, {
    id: d.userCode,
    username: d.userName,
    avatar: USER_AVATAR
  })
  )
    .sort((me, other) => me.lastActiveDate >= other.lastActiveDate ? -1 : 1
    )

    .sort(me => me.userState === 'O' ? -1 : 1
    )
}
