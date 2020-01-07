import {findUpProperty} from '../utils'
import defaultOptions from './options'

export default {
  props: {
    gutter: Number,

    span: Number,

    push: Number,

    zIndex: Number,

    wrap: Boolean,

    inline: Boolean,

    reverse: Boolean,

    noGutter: Boolean,

    background: String,

    padding: String,

    width: String,

    height: String,

    center: Boolean,

    start: Boolean,

    end: Boolean,

    top: Boolean,

    middle: Boolean,

    bottom: Boolean,
  },

  computed: {
    layoutStyle() {
      const style = {}
      const span = this.span
      const parentFlexDirection = this.parentFlexDirection()
      const inline = this.parentProperty('inline')

      // 槽宽
      if (!this.noGutter) {
        style.padding = `${this.currentGutter / 2}px`
      }

      if (this.padding) {
        style.padding = this.padding
      }

      if (this.zIndex) {
        style.zIndex = this.zIndex
      }

      if (this.background) {
        style.background = this.background
      }

      // Grids 布局
      if (this.parentProperty('wrap') && !inline) {
        let size = 100

        if (span) {
          size *= span / this.currentGrids
        }

        style.flexBasis = `${size}%`

        if (parentFlexDirection === 'row') {
          style.maxWidth = `${size}%`
        } else if (parentFlexDirection === 'column') {
          style.maxHeight = `${size}%`
        }
      } else if (span) {
        style.flexGrow = span
        style.flexShrink = 1 / span
      }

      if (inline) {
        style.flexGrow = 0
        style.flexShrink = 0
        style.flexBasis = 'auto'
      }

      // push
      if (this.push) {
        const push = 100 * this.push / this.currentGrids

        if (parentFlexDirection === 'row') {
          style.marginLeft = `${push}%`
        } else if (parentFlexDirection === 'column') {
          style.marginTop = `${push}%`
        }
      }

      if (this.width) {
        style.width = this.width
      }

      if (this.height) {
        style.height = this.height
      }

      return style
    },

    layoutClass() {
      const classes = {
        reverse: this.reverse,
        wrap: this.wrap,
        'no-fit': this.noFit,
        start: this.start,
        center: this.center,
        end: this.end,
        top: this.top,
        middle: this.middle,
        bottom: this.bottom,
      }

      if (this.zIndex) {
        classes[`zpa-shadow-${this.zIndex}`] = true
      }

      return classes
    },

    noFit() {
      return !!(this.width || this.height)
    },

    currentGutter() {
      return findUpProperty(this, 'gutter', defaultOptions.gutter)
    },

    currentGrids() {
      return findUpProperty(this, 'grids', defaultOptions.grids)
    },
  },

  methods: {
    parentFlexDirection() {
      let parent = this.$parent

      while (parent) {
        if (parent.flexDirection instanceof Function) break
        parent = parent.$parent
      }

      if (parent) return parent.flexDirection()
    },

    parentProperty(key) {
      let parent = this.$parent
      while (parent) {
        // 通过 flexDirection 属性判断是不是 row 或者 column 组件
        if (parent.flexDirection instanceof Function) break
        parent = parent.$parent
      }

      if (parent) return parent[key]
    },
  },
}
