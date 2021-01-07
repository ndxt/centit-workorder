<template>
  <DataList
    ref="Table"
    id="stuffTable"
    :height="height"
    :operWidth="80"
    :columns="attentionMaterialColumns"
    :query="queryAttentionMaterial"
  >
    <template slot-scope="props">
      <button class="download" type="button" @click="download(props.row)">下载</button>
      <!--<a class="download" :href="'workflow/download/pfile/' + props.row.fileId">下载</a>-->
    </template>
  </DataList>
</template>

<script>
  import attentionMaterialColumns from '../columns/attentionMaterialColumns'

  import {queryAttentionMaterial,} from '@/api/task/attention'

  export default {
  name: 'AttentionMaterialList',

  props: {
    value: Object,
  },

  data() {
    return {
      attentionMaterialColumns,
      height: 'auto',
    }
  },

  methods: {
    queryAttentionMaterial,

    async reload() {
      const params = {
        flowInstId: this.value.flowInstId,
      }

      await this.$refs['Table'].load({params})
    },

    setHeight() {
      const ifm = document.getElementById('stuffTable')

      if (ifm != null) {
        this.height = ifm.clientHeight + 50
        this.$emit('setParentHeight', this.height)
      }
    },

    download(data) {
      window.open(`workflow/download/pfile/${data.fileId}`)
    },
  },
}
</script>

<style lang="less">
  .download {
    border: 1px solid #2db7f5;
    color: #2db7f5;
    padding: 2px 5px;
    background: rgba(0, 0, 0, 0);
    border-radius: 3px;
    cursor: pointer;
    vertical-align: middle;
  }
</style>
