<template>
  <ConfirmOperator
    type="error"
    text="从当前版本编辑"
    title="从当前版本编辑"
    :content="content"
  />
</template>

<script>
import {
  changeFlowVersion,
} from '@/api/workflow/flowDefine'
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

export default {
  name: 'ChangeFlowDefineVersionOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  computed: {
    content() {
      return `是否确定还原草稿至当前版本？`
    },
  },

  methods: {
    onSubmit() {
      const {
        flowCode,
        version,
      } = this.value

      return changeFlowVersion({
        flowCode,
        version,
      })
    },
  },
}
</script>
