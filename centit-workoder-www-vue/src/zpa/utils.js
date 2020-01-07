export function findUpProperty(node, key, defaultValue) {
  let value

  while (node) {
    value = node[key]
    if (value !== undefined) return value
    node = node.$parent
  }

  return defaultValue
}

export function getUuid() {
  return new Date().getTime()
}

export function buildTree(data) {
  if (!data) {
    return []
  }

  const temp = {}
  const tree = []

  data.forEach(v => {
    v.checked = false
    v.children = []
    if (!v.key) {
      v.key = v.id
    }
    if (!v.parent) {
      v.parent = v.pid
    }
    if (!v.title) {
      v.title = v.text
    }
    temp[v.key] = JSON.parse(JSON.stringify(v))
  })

  for (const key in temp) {
    if (temp[key].parent !== 0 && temp[temp[key].parent]) {
      // if (!temp[temp[key].parent].children) {
      //   temp[temp[key].parent].children = []
      // }

      temp[temp[key].parent].expand = true
      temp[temp[key].parent].children.push(temp[key])
    } else {
      temp[key].parent = 0
      tree.push(temp[key])
    }
  }

  return tree
}

export function flatTree(tree, level = 0, hide = false) {
  let flat = []

  for (let i = 0; i < tree.length; i++) {
    const node = tree[i]
    node._level = level
    node._hide = hide
    flat.push(node)

    if (node.children instanceof Array) {
      const h = hide || !node._expand
      flat = flat.concat(flatTree(node.children, level + 1, h))
    }
  }

  return flat
}

export function dateToString(value) {
  let date = new Date()

  if (value) {
    date = new Date(value)
  }

  const y = date.getFullYear()
  const m = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
  const d = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()

  return `${y}-${m}-${d}`
}

export function deepCopy(obj) {
  return JSON.parse(JSON.stringify(obj))
}

// 将数组指定位置的元素上移一位
export function arrayUpGo(array, index) {
  if (index !== 0) {
    array[index] = array.splice(index - 1, 1, array[index])[0]
  } else {
    array.push(array.shift())
  }
}

// 将数组指定位置的元素下移一位
export function arrayDownGo(array, index) {
  if (index !== array.length - 1) {
    array[index] = array.splice(index + 1, 1, array[index])[0]
  } else {
    array.unshift(array.splice(index, 1)[0])
  }
}
