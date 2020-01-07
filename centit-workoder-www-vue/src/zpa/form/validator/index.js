import Schema from 'async-validator'
import Mustache from 'mustache'
import _ from 'lodash'
import cn, {defaultItemName} from './locale/cn'
import custom from './custom'

export function validate({error, value, rules} = {}) {
  error = error || this.error
  value = this.validateValue === undefined ? (value || this.value) : this.validateValue
  rules = rules || this.rules

  return new Promise(resolve => {
    // 如果没有校验规则，直接返回
    if (!rules) resolve()
    const label = rules.label

    // 非空初始值不予判断
    if (this.initialValue && value === this.initialValue) {
      return resolve()
    }

    // 值没有改变，直接返回错误
    if (error && value === error.value) return resolve(error)

    this.validating = true
    const validator = new Schema(rules)
    validator.messages(cn)

    try {
      validator.validate({[label]: value}, errors => {
        this.validating = false

        if (!errors) {
          this.error = ''
          this.success = true
          return resolve()
        }

        // 保存错误信息
        const messages = errors.map(e => Mustache.render(e.message, this))
        this.success = false
        this.validating = false
        this.error = {
          value,
          messages,
        }
        resolve(messages)
      })
    } catch (e) {
      this.validating = false
      resolve([e])
    }
  })
}

export function parseValidators2Rules(validates, message) {
  let rules = []

  if (!validates) return []

  /**
   * string: Must be of type string. This is the default type.
   * number: Must be of type number.
   * boolean: Must be of type boolean.
   * method: Must be of type function.
   * regexp: Must be an instance of RegExp or a string that does not generate an exception when creating a new RegExp.
   * integer: Must be of type number and an integer.
   * float: Must be of type number and a floating point number.
   * array: Must be an array as determined by Array.isArray.
   * object: Must be of type object and not Array.isArray.
   * enum: Value must exist in the enum.
   * date: Value must be valid as determined by Date
   * url: Must be of type url.
   * hex: Must be of type hex.
   * email: Must be of type email.
   */
  if (_.isString(validates)) {
    const c = custom[validates]

    if (!c) {
      rules.push({
        type: validates,
        message,
      })
    } else {
      // 自定义
      const r = parseValidators2Rules(c.validator, c.message || message)
      if (r) rules = rules.concat(r)
    }
  } else if (_.isRegExp(validates)) {
    rules.push({
      pattern: validates,
      message,
    })
  } else if (_.isFunction(validates)) {
    rules.push({
      async validator(rule, value, cb) {
        try {
          const result = await validates(value)

          if (result === true) {
            return cb()
          }

          return cb(new Error(result))
        } catch (e) {
          return cb(e)
        }
      },
      message,
    })
  } else if (_.isArray(validates)) {
    validates.forEach(v => {
      const r = parseValidators2Rules(v, v.message || message)
      if (r) rules = rules.concat(r)
    })
  } else if (_.isObject(validates)) {
    rules.push({
      ...validates,
      message: Mustache.render(validates.message || message, validates)
    })
  }

  return rules
}

export function getRules({label, rules} = {}) {
  label = label || defaultItemName
  rules = rules || []

  const {required, min, max, len, pattern, type} = this

  if (this.disabled || this.readonly) return

  if (['email', 'phone', 'url'].includes(type)) {
    rules = [...rules, ...parseValidators2Rules(type)]
  }

  if (required) {
    pushRule({required})
  }

  if (len) {
    pushRule({len})
  } else if (min && max) {
    pushRule({min, max})
  } else if (min) {
    pushRule({min})
  } else if (max) {
    pushRule({max})
  }

  if (pattern) {
    pushRule({pattern})
  }

  // 如果有校验规则，返回规则
  if (rules.length) {
    return {
      label,
      [label]: rules,
    }
  }

  function pushRule(rule) {
    const message = rule.message

    rules.push({
      type,
      ...rule,
      message: message ? Mustache.render(message, rule) : undefined
    })
  }
}
