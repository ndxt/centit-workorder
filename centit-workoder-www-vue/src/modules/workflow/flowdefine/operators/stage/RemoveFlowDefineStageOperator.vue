<template>
  <ConfirmOperator
    type="error"
    text="删除"
    title="删除"
    :content="content"
  />
</template>

<script>
  import {addFlowDefineStage,} from '@/api/workflow/flowDefine'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'RemoveFlowDefineStageOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  props: {
    obj: Object,
  },

  computed: {
    content() {
      return `确认删除阶段[${this.value.stageName}]吗？`
    },
  },

  methods: {
    onSubmit() {
      if (this.obj.flowStages && this.obj.flowStages.length) {
        this.obj.flowStages = this.obj.flowStages.filter(d => {
          return this.value.stageId !== d.stageId
        })
      }

      const {
        flowCode,
        flowStages,
      } = this.obj

      return addFlowDefineStage({
        flowCode,
        flowStages,
      })
    },
  },
}
</script>
