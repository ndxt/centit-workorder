<template>
  <SearchLayout>
    <SearchBox slot="search" @search="search">
      <zpa-text-input label="文档名称" v-model="params.moduleName" />
    </SearchBox>

    <DataList
      index
      ref="Table"
      :operWidth="280"
      :columns="flowDocumentManagerColumns"
      :query="queryFlowDocument"
    >

    </DataList>
  </SearchLayout>
</template>

<script>
  import {queryFlowDocument} from '@/api/workflow/flowDocumentManager'

  import flowDocumentManagerColumns from './flowDocumentManagerColumns'

  export default {
  name: 'PageFlowDocumentManager',

  data() {
    return {
      flowDocumentManagerColumns,
      params: {},
    }
  },

  components: {},

  methods: {
    queryFlowDocument,

    search() {
      const params = this.params
      return this.$refs.Table.load({params})
    },
  },
}
</script>

<style scoped>

</style>
