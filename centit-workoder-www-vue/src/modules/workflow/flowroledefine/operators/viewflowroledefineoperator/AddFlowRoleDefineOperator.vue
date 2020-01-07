<template>
  <ModalOperator text="新增" title="新增" :width="600" :ghost="false">
    <zpa-form ref="Form">
      <zpa-group>
        <zpa-select label="关联类型" filterable v-model="data.relatedType"
                    :values="relatedTypes" required/>
        <!--<zpa-text-input
          label="关联类型"
          v-model="data.relatedType"
          :max="20"
          required
        />-->
        <DictionarySelect v-if="data.relatedType==='xz'" filterable code="RankType"
                          label="职务编码"  v-model="data.relatedCode" required />
        <RoleSelect v-if="data.relatedType==='js'"
            label="角色编码"
            v-model="data.relatedCode"
            :department="top.dept"
            required
          />
      </zpa-group>
    </zpa-form>
  </ModalOperator>
</template>

<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
import {addFlowRoleDefine, } from '@/api/workflow/flowroledefine'
import DictionaryBaseInfo from '../../../../dictionary/DictionaryBaseInfo.vue';
export default {
  components: {DictionaryBaseInfo},
  name: 'AddFlowRoleDefineOperator',
  mixins: [
    ModalOperatorMixin,
  ],
  data() {
    return {
      relatedTypes: [
        {value: 'xz', text: '职务'},
        {value: 'js', text: '角色'},
      ],
    }
  },
  inject: ['top'],
  methods: {
    // async beforeOpen() {
    //   this.data.optReq = 'R'
    //   this.data.optId = this.top.value.optId
    // },
    beforeSubmit() {
      return this.$refs['Form'].validate()
    },
    onSubmit() {
      this.data.id = '';
      this.data.roleCode = this.value.roleCode
      const apprRoleDefineList = [ this.data ]
      return addFlowRoleDefine({ apprRoleDefineList: apprRoleDefineList })
    },
  },

}
</script>
