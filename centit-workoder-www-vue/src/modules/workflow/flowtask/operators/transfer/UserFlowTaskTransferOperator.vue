<template>
  <ModalOperator :type="modalParam.type" :text="modalParam.text" :title="modalParam.text" :disabled="modalParam.disabled">
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input
          readonly
          label="来源用户"
          v-model="data.formUser"
        />
        <UserSelect
          required
          filterable
          clearable
          label="目标用户"
          tag="A"
          v-model="data.toUser"
          @input="reload"
        />
        <zpa-textarea
          label="描述"
          v-model="data.moveDesc"
          :max="85"
        />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>
  import {mapState,} from 'vuex'
  import {moveFlowTaskTransfer, moveFlowTaskTransferSelect,} from '@/api/workflow/flowTask'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'UserFlowTaskTransferOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  props: {
    value: Object,
    type: String,
    checkedList: Array,
  },

  computed: {
    ...mapState({
      currentUser: 'currentUser',
    }),

    modalParam() {
      switch (this.type) {
        case 'all':
          return {
            type: 'error',
            text: '全部转移',
            disabled: !this.value.userCode,
          }
        case 'batch':
          return {
            type: 'warning',
            text: '批量转移',
            disabled: !this.value.userCode || !this.checkedList.length,
          }
        case 'single':
          return {
            type: 'primary',
            text: '单个转移',
            disabled: false,
          }
      }
    },
  },

  methods: {
    beforeOpen() {
      this.data = Object.assign({}, {
        formUser: this.value.userCode,
        operatorUser: this.currentUser,
      })

      switch (this.type) {
        case 'batch':
          if (this.checkedList.length) {
            const ids = this.checkedList.map(m => m.nodeInstId)

            this.$set(this.data, 'nodeInstIds', ids)
          }
          break
        case 'single':
          this.$set(this.data, 'nodeInstIds', [this.value.nodeInstId])
          break
      }
    },

    beforeSubmit() {
      return this.$refs.Form.validate()
    },

    onSubmit() {
      switch (this.type) {
        case 'all':
          return moveFlowTaskTransfer(this.data)
        case 'batch':
        case 'single':
          return moveFlowTaskTransferSelect(this.data)
      }

      return true
    },
  },
}
</script>
