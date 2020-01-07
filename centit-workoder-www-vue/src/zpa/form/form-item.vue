<template>
  <zpa-column class="zpa-input" :class="className" :span="span" :push="push">
    <zpa-row class="title" height="auto" no-gutter v-if="hasTopLabel">
      <label class="no-fit" :for="uuid">{{label}}</label>
    </zpa-row>
    <zpa-row class="input" height="auto" middle :gutter="0">
      <label class="no-fit" :for="uuid" :style="labelStyle" v-if="hasAsideLabel">{{label}}</label>
      <zpa-row :style="inputWrapStyle">
        <slot></slot>
      </zpa-row>
    </zpa-row>
    <zpa-column class="message" height="auto" no-gutter :style="messageStyle" v-if="error">
      <p v-for="(e, i) in error.messages" :key="i" class="error">
        {{e}}
      </p>
    </zpa-column>
  </zpa-column>
</template>

<script>
import { findUpProperty } from '../utils'
import defaultOptions from './options'

export default {
  name: 'FormItem',

  props: {
    error: [Object, String, Array],

    success: Boolean,

    validating: Boolean,

    required: Boolean,

    readonly: Boolean,

    disabled: Boolean,

    // layout begin
    span: Number,

    push: Number,
    // layout end

    uuid: String,

    // style begin
    label: String,

    labelWidth: Number,

    labelAlign: String,

    itemHeight: Number,
    // style end
  },

  inject: ['form'],

  computed: {

    // style begin
    hasTopLabel() {
      return this.label && this.$labelAlign === 'top'
    },

    hasAsideLabel() {
      return this.label && this.$labelAlign === 'left'
    },

    $labelWidth() {
      return findUpProperty(this, 'labelWidth', defaultOptions.labelWidth)
    },

    $labelAlign() {
      return findUpProperty(this, 'labelAlign', defaultOptions.labelAlign)
    },

    inputWrapStyle() {
      const style = {}

      if (!this.span) {
        style.minWidth = '160px'
      }

      return style
    },

    labelStyle() {
      const style = {}
      const width = this.$labelWidth
      const align = this.$labelAlign

      if (width && align === 'left') {
        style.width = `${width}px`
      }

      return style
    },

    messageStyle() {
      const style = {}
      const width = this.$labelWidth
      const align = this.$labelAlign

      if (width && align === 'left') {
        style.marginLeft = `${width}px`
      }

      return style
    },
    // style end

    className() {
      return {
        required: this.required,
        disabled: this.disabled,
        readonly: this.readonly,
        validating: this.validating,
        error: this.error,
        success: this.success,
      }
    },
  },
}
</script>

<style scoped lang="less">
  .zpa-input {
    color: #515a6e;

    label {
      padding: 0 4px 2px 0;
      text-align: right;

      &:after {
        content: ':';
      }
    }

    /* 必填 */
    &.required {
      label:before {
        content: '*';
        color: red;
      }
    }

    .input {
      input[type=text] {
        width: 100%;
        height: 32px;
        padding: 0 4px;
        border: 1px solid #dcdee2;
        border-radius: 4px;
        outline: none;
        color: #515a6e;
      }
    }

    .message {
      .error {
        padding: 2px 0;
        color: red;
      }
    }

    &.error {
      .input {
        input[type=text] {
          border: 1px solid #f009;
          background: #f002;
        }
      }
    }

    &.success {
      .input {
        input[type=text] {
          border: 1px solid #0f06;
          background: #0f02;
        }
      }
    }

    &.validating {
      .input {
        opacity: .4;
      }
    }
  }
</style>
