<template>
  <ModalOperator customButton ref="ModalOperator" text="编辑" title="编辑数据库">
    <zpa-form ref="Form">
      <DatabaseBaseInfo v-model="data"/>
    </zpa-form>

    <div slot="customBtn">
      <Button type="primary" @click="link">链接测试</Button>
      <Button type="primary" @click="ok">更新</Button>
      <Button type="text" @click="close">取消</Button>
    </div>
  </ModalOperator>
</template>

<script>
  import {getDatabase, modifyDatabase, testConnectOs,} from '@/api/admin/business'

  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import DatabaseBaseInfo from '../DatabaseBaseInfo'

  export default {
  name: 'EditDatabaseOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  components: {
    DatabaseBaseInfo,
  },

  methods: {
    async beforeOpen() {
      this.data = await getDatabase(this.value.databaseCode)
    },

    link() {
      let _this = this

      testConnectOs(this.data).then(function(testData) {
        _this.$Message.success('数据库连接测试成功！')
      },

      function(reason, testData) {
        _this.$Message.error('数据库连接测试失败！')
      })
    },

    ok() {
      return this.$refs['ModalOperator'].ok()
    },

    close() {
      return this.$refs['ModalOperator'].close()
    },

    beforeSubmit() {
      return this.$refs['Form'].validate()
    },

    onSubmit() {
      return modifyDatabase(this.data.databaseCode, this.data)
    },
  },
}
</script>
