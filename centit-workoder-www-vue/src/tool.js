// function getTree(data, result, fields = [], defaults = []) {
//   data.forEach((element, index) => {
//     const obj = {
//       ...addFields(element, fields),
//       title: element.text,
//       expand: true,
//       children: [],
//       checked: false,
//     }
//
//     if (defaults.length) {
//       obj.checked = defaults.includes(element.optCode)
//     }
//
//     result.push(obj)
//
//     if (element.children) {
//       getTree(element.children, result[index].children, defaults, fields)
//     } else {
//       if (element.optMethods) {
//         const childe = []
//
//         element.optMethods.forEach(element => {
//           const obj = {
//             ...addFields(element, fields),
//             title: element.optName,
//             checked: false,
//           }
//
//           if (defaults.length) {
//             obj.checked = defaults.includes(element.optCode)
//           }
//
//           childe.push(obj)
//         })
//
//         result[index].children = childe
//       }
//     }
//   })
//   return result
// }
//
// function addFields(element, fields) {
//   const res = {}
//
//   if (fields.length > 0) {
//     for (const key in element) {
//       if (fields.includes(key)) {
//         res[key] = element[key]
//       }
//     }
//   }
//
//   return res
// }
//
// export {
//   getTree
// }

export function makeTree(data, isParent, childField) {
  isParent = isParent || function (parent) {
    return this.pid === parent.id
  }

  let top = []
  childField = childField || 'children'

  data.forEach(function (child) {
    let isTop = true

    data.forEach(function (parent) {
      if (isParent.call(child, parent)) {
        if (!parent[childField]) {
          parent[childField] = []
        }
        parent[childField].push(child)
        isTop = false
        return false
      }
    })

    if (isTop) {
      top.push(child)
    }
  })

  return top
}

/**
 * 遍历树
 * @param datas
 * @param callback
 * @parem childField
 * @param level
 */
export function walkTree(datas, callback, childField, level) {
  childField = childField || 'children'
  level = level || 0
  callback = callback || function () {
  }

  let _walk = function (data, callback, parent, level) {
    data.forEach(function (obj, index) {
      let children = obj[childField]

      if (children && children.length) {
        _walk(children, callback, obj, level + 1)
      }

      // 当前对象、父级对象、层级、当前对象的index
      callback.call(obj, obj, parent, level, index)
    })
  }

  if (datas.length) {
    _walk(datas, callback, null, 0)
  } else {
    _walk([datas], callback, null, 0)
  }
}

/**
 * 遍历树
 * @param datas
 * @param callback
 * @parem childField
 * @param level
 */
export function walkTreeBefore(datas, callback, childField, level) {
  childField = childField || 'children'
  level = level || 0
  callback = callback || function () {
  }

  let _walk = function (data, callback, parent, level) {
    data.forEach(function (obj, index) {
      // 当前对象、父级对象、层级、当前对象的index
      callback.call(obj, obj, parent, level, index)

      let children = obj[childField]

      if (children && children.length) {
        _walk(children, callback, obj, level + 1)
      }
    })
  }

  if (datas.length) {
    _walk(datas, callback, null, 0)
  } else {
    _walk([datas], callback, null, 0)
  }
}
