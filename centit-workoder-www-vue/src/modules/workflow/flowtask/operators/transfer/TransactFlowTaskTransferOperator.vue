<template>
  <ModalOperator type="success" text="办理" title="办理" width="1000">
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
  import {listPageByOptCode,} from '@/api/workflow/flowBusiness'
  import {getFormaccess,} from '@/api/workflow/flowTask'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'TransactFlowTaskTransferOperator',

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
        optCode,
        userCode,
        unitCode,
        flowCode,
        flowInstId,
        flowOptTag,
        nodeInstId,
      } = this.value
      const res = await listPageByOptCode(optCode)
      const def = await getFormaccess({
        flowInstId,
        modelId: res.pageUrl,
      })

      this.modelId = res.pageUrl
      this.initialData = {
        userCode,
        unitCode,
        flowCode,
        flowInstId,
        flowOptTag,
        nodeInstId,
        currentOperatorUserCode: userCode,
        ...def,
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
