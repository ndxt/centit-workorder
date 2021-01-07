<template>
  <ModalOperator text="当前岗位" title="当前岗位" type="primary" iconType="md-contact" iconSize="18">
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-select
          v-model="id"
          label="当前用户所有职位"
          :labelWidth="120"
          :query="getUserstations"
        />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>
<script>
  import {getCurrposition, queryPositions, setPosition,} from '@/api/login'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'

  export default {
  name: 'UserRank',

  mixins: [
    ModalOperatorMixin,
  ],

  data() {
    return {
      id: '',
    }
  },

  computed: {

  },

  methods: {
    async getUserstations() {
      const res = await queryPositions()

      return res.map(m => {
        return {
          value: m.userUnitId,
          text: m.valueField,
        }
      })
    },

    async beforeOpen() {
      let currentuser = await getCurrposition()

      this.id = currentuser.userUnitId
    },

    onSubmit() {
      return setPosition(this.id)
    },
  },
}
</script>
