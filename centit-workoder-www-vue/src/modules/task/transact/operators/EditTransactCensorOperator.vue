<template>
  <ModalOperator ref="Model" footerHide text="选择" title="选择" :width="960">
    <TreeTransfer
      filter
      :data="all"
      :targetKeys="key"
      @changeTargetKeys="changeKey"
    />
  </ModalOperator>
</template>

<script>
  import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin'
  import {queryTransactAttention, queryTransactEngine, queryTransactOrg, queryTransactTeam,} from '@/api/task/transact'
  import {buildTree} from '@/zpa/utils'

  export default {
  name: 'EditTransactCensorOperator',

  mixins: [
    ModalOperatorMixin,
  ],

  props: {
    value: Object,
    type: String,
  },

  data() {
    return {
      all: [],
      key: [],
      original: [],
    }
  },

  computed: {
    params() {
      return {
        flowInstId: this.value.initData.flowInstId,
        nodeInstId: this.value.initData.nodeInstId,
        attentionExp: this.value.initData.moduleParam.attentionFilter,
        engineCode: this.value.initData.moduleParam.engineroleCode,
        engineExp: this.value.initData.moduleParam.engineroleFilter,
        orgExp: this.value.initData.moduleParam.zbOrgroleFilter,
        orgRoleCode: this.value.initData.moduleParam.zbOrgroleCode,
        teamRoleCode: this.value.initData.moduleParam.teamroleCode,
        teamRoleExp: this.value.initData.moduleParam.teamroleFilter,
        userExp: this.value.initData.moduleParam.attentionFilter,
        xborgExp: this.value.initData.moduleParam.xbOrgroleFilter,
        xborgRoleCode: this.value.initData.moduleParam.xbOrgroleCode,
      }
    },
  },

  methods: {
    async beforeOpen() {},

    reload() {
      switch (this.type) {
        case 'teamUserCodes':
          this.queryTeam()
          break
        case 'engineUserCodes':
          this.queryEngine()
          break
        case 'attentionUserCodes':
          this.queryAttention()
          break
        case 'zbOrgCode':
          this.queryOrg()
          break
        case 'xbOrgCode':
          this.queryOrg('xborgRoleCode')
          break
      }
    },

    async queryTeam() {
      const res = await queryTransactTeam(this.params)

      this.original = res.teamListTree

      this.all = buildTree(this.original)

      if (res.teamJsons && res.teamJsons.length) {
        // this.key = res.teamJsons.map(m => m.nodeID)
        this.$emit('changeKey', {
          key: this.key,
          type: this.type,
          // name: res.teamJsons.map(m => m.name).join(', '),
        })
      }
    },

    async queryEngine() {
      const res = await queryTransactEngine(this.params)

      this.original = res.engineListTree

      this.all = buildTree(this.original)

      if (res.engineJsons && res.engineJsons.length) {
        // this.key = res.engineJsons.map(m => m.nodeID)
        this.$emit('changeKey', {
          key: this.key,
          type: this.type,
          // name: res.engineJsons.map(m => m.name).join(', '),
        })
      }
    },

    async queryAttention() {
      const res = await queryTransactAttention(this.params)

      this.original = res.attentionListTree

      this.all = buildTree(this.original)

      if (res.attentionJsons && res.attentionJsons.length) {
        // this.key = res.attentionJsons.map(m => m.nodeID)
        this.$emit('changeKey', {
          key: this.key,
          type: this.type,
          // name: res.attentionJsons.map(m => m.name).join(', '),
        })
      }
    },

    async queryOrg(type) {
      const {
        flowInstId,
        nodeInstId,
        orgExp,
        orgRoleCode,
        xborgRoleCode,
      } = this.params
      const res = await queryTransactOrg({
        flowInstId,
        nodeInstId,
        orgExp,
        orgRoleCode: !type ? orgRoleCode : xborgRoleCode,
      })

      this.original = res.orgListTree

      this.all = buildTree(this.original)

      if (res.orgList && res.orgList.length) {
        // this.key = res.orgList.map(m => m.nodeID)
        this.$emit('changeKey', {
          key: this.key,
          type: this.type,
          // name: res.orgList.map(m => m.name).join(', '),
        })
      }
    },

    changeKey(data) {
      const res = this.original.filter(d => data.includes(d.id))

      this.key = data
      this.$emit('changeKey', {
        key: data,
        type: this.type,
        name: res.map(m => m.name).join(', '),
      })
    },
  },

  mounted() {
    this.reload()
  },
}
</script>
