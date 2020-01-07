<template>
  <zpa-form-item
    :error="error"
    :success="success"
    :validating="validating"
    :required="required"
    :readonly="readonly"
    :disabled="disabled"
    :uuid="uuid"
    :span="span"
    :push="push"
    :label="label"
    :labelWidth="labelWidth"
    :labelAlign="labelAlign"
    :itemHeight="itemHeight"
  >
    <DatePicker
      style="width: 50%;"
      :value="value[startField]"
      :options="startOptions"
      placeholder="开始时间"
      :editable="false"
      :readonly="readonly"
      :disabled="disabled"
      @on-change="onChangeStart"
    ></DatePicker>
    <span>~</span>
    <DatePicker
      style="width: 50%;"
      :value="value[endField]"
      :options="endOptions"
      placeholder="结束时间"
      :editable="false"
      :readonly="readonly"
      :disabled="disabled"
      @on-change="onChangeEnd"
    ></DatePicker>
  </zpa-form-item>
</template>

<script>
import inputMixin from './mixins/input'
import {dateToString} from '../utils'

export default {
  name: 'zpaDateInput',

  mixins: [inputMixin],

  data() {
    return {
      validateValue: '',

      startOptions: {
        disabledDate: date => {
          if (!this.endField || !this.value[this.endField]) {
            return false
          }

          return dateToString(date) > this.value[this.endField]
        }
      },
      endOptions: {
        disabledDate: date => {
          if (!this.startField || !this.value[this.startField]) {
            return false
          }

          return dateToString(date) < this.value[this.startField]
        }
      },
    }
  },

  props: {
    value: Object,
    startField: String,
    endField: String,
  },

  computed: {},

  methods: {
    onChangeStart(data) {
      this.value[this.startField] = data
      this.validateValue = data
      this.debounceValidate()

      this.endOptions = {
        disabledDate: date => {
          if (!data) {
            return false
          }

          return dateToString(date) < this.value[this.startField]
        }
      }
    },
    onChangeEnd(data) {
      this.value[this.endField] = data
      this.debounceValidate()

      this.startOptions = {
        disabledDate: date => {
          if (!data) {
            return false
          }

          return dateToString(date) > this.value[this.endField]
        }
      }
    },
  },

  created() {
    // if (!this.value[this.startField]) {
    //   this.value[this.startField] = dateToString()
    // }
  },
}
</script>

<style scoped lang="less">
  span {
    display: flex;
    margin: 0 5px;
    align-items: center;
  }
</style>
