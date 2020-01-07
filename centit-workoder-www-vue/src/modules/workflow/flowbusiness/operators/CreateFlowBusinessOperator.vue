<template>
  <ModalOperator type="success" text="创建流程" title="创建流程">
    <FormView
      ref="FormView"
      :id="modelId"
      :params="params"
      :i18n="i18n"
      :autoLoad="false"
      :initialData="initialData"
    />
  </ModalOperator>
</template>

<script>
import {
  getFormaccess,
} from '@/api/workflow/flowBusiness'

import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'CreateFlowBusinessOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  data() {
    return {
      modelId: '',
      initialData: {},
    }
  },

  props: {
    submitType: {
      type: String,
      default: 'SUBMIT',
    },
    params: Object,
  },

  computed: {
    i18n() {
      return false
    },
  },

  methods: {
    async beforeOpen() {
      const {
        optUrl,
        titleTemplate,
        defaultFlow,
      } = this.value
      const res = await getFormaccess({
        modelId: optUrl,
      })

      this.modelId = optUrl
      this.initialData = {
        titleTemplate,
        flowCode: defaultFlow,
        ...res,
      }
    },

    beforeSubmit() {
      return this.$refs.FormView.validate()
    },

    async onSubmit() {
      const data = {
        data: this.$refs.FormView.data,
        params: this.params,
      }

      switch (this.submitType) {
        case 'POST':
          await this.$refs.FormView.save(data)
          break
        case 'PUT':
          await this.$refs.FormView.update(data)
          break
        case 'SUBMIT':
          await this.$refs.FormView.submit(data)
          break
        default:
          return
      }

      return true
    },
  },
}
</script>
