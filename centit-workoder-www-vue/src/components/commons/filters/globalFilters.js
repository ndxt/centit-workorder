const day = function (value) {
  if (!value) {
    return '-'
  }

  let date = new Date(value)
  let Y = date.getFullYear() + '-'
  let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
  let D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate())
  return Y + M + D
}

const timeType = function (value) {
  if (typeof value === 'string') {
    try {
      value = value.replace(/-/g, '/')
    } catch (error) {
      
    }
  }

  if (!value) {
    return '-'
  }

  let date = new Date(value)
  let Y = date.getFullYear() + '-'
  let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
  let D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate())
  let H = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
  let Mi = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
  let S = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
  return Y + M + D + ' ' + H + ':' + Mi + ':' + S
}

function yesOrNo(value) {
  if (value === true || value === 1 || value === 'T') {
    return '是'
  }

  if (value === false || value === 0 || value === 'F') {
    return '否'
  }

  return value
}

export default {
  day,
  yesOrNo,
  timeType,
}
