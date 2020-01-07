export default {
  'default': '%s 校验失败',
  required: '%s 是必填项',
  'enum': '%s 必须是其中的一个值： %s',
  whitespace: '%s 不能为空',
  date: {
    format: '%s 日期 %s 不符合格式： %s',
    parse: '%s 日期 不能被转换, %s 不正确 ',
    invalid: '%s 日期 %s 不正确'
  },
  types: {
    string: '%s 不是一个 %s',
    method: '%s 不是一个 %s (function)',
    array: '%s 不是一个 %s',
    object: '%s 不是一个 %s',
    number: '%s 不是一个 %s',
    date: '%s 不是一个 %s',
    boolean: '%s 不是一个 %s',
    integer: '%s 不是一个 %s',
    float: '%s 不是一个 %s',
    regexp: '%s 不是一个 %s',
    email: '%s 不是一个 %s',
    url: '%s 不是一个 %s',
    hex: '%s 不是一个 %s'
  },
  string: {
    len: '%s 长度必须为 %s',
    min: '%s 最少长度 %s',
    max: '%s 最大长度 %s',
    range: '%s 长度必须在 %s 到 %s 之间'
  },
  number: {
    len: '%s 必须等于 %s',
    min: '%s 不能小于 %s',
    max: '%s 不能大于 %s',
    range: '%s 必须在 %s 到 %s 之间'
  },
  array: {
    len: '%s 长度必须为 %s',
    min: '%s 最少长度 %s',
    max: '%s 最大长度 %s',
    range: '%s 长度必须在 %s 到 %s 之间'
  },
  pattern: {
    mismatch: '%s 的值 %s 不符合 %s'
  }
}

export const defaultItemName = '此项'
