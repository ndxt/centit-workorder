export default {
  // 主键 a-z A-Z 0-9 _-
  primary: {
    validator: /^[a-zA-Z0-9_-]+$/,
    message: '{{label}} 只能为数字、字母的组合',
  },

  word: {
    validator: /^[a-zA-Z_-]+$/,
    message: '{{label}} 只能为英文字母',
  },

  email: {
    validator: /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/,
    message: '请填写正确的邮箱地址',
  },

  phone: {
    validator: /(^[0-9]{3,4}-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0?1[0-9]{10}$)/,
    message: '请填写正确的电话号码',
  },

  url: {
    validator: /^(?:([A-Za-z]+):)?(\/{0,3})([0-9.\-A-Za-z]+)(?::(\d+))?(?:\/([^?#]*))?(?:\?([^#]*))?(?:#(.*))?$/,
    message: '请填写正确的网址',
  },
}
