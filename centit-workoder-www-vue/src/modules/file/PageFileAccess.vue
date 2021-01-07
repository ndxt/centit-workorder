<template>
  <AsideLayout no-gutter>

    <SearchLayout>

      <SearchBox slot="search" @search="reload">
        <zpa-text-input label="名称" v-model="params.name"/>
        <zpa-select
          label="类别"
          v-model="params.osId"
          valueField="osId"
          textField="osName"
          :query="manageSelect"
        />
      </SearchBox>

      <DataList
        ref="Table"
        index
        :columns="fileAccessColumns"
        :query="queryAccess"
        @on-selected-change="selectedChange"
      ></DataList>

    </SearchLayout>

    <PageFileAside v-if="currentAccess" slot="aside" :current="currentAccess"/>

  </AsideLayout>
</template>

<script>
  import fileAccessColumns from './columns/fileAccessColumns'

  import PageFileAside from './PageFileAside'

  import {queryAccess, queryManage,} from '@/api/admin/file'

  export default {
  name: 'PageFileAccess',

  data() {
    return {
      fileAccessColumns,

      params: {},

      currentAccess: undefined,
    }
  },

  components: {
    PageFileAside,
  },

  methods: {
    queryAccess,

    reload() {
      const params = this.params

      this.$refs['Table'].load({params})
    },

    async manageSelect() {
      const res = await queryManage()

      return res.map(m => {
        const {
          osId,
          osName,
        } = m

        return {
          osId,
          osName,
        }
      })
    },

    selectedChange(data) {
      this.currentAccess = data
    },
  },

  mounted() {
    this.$nextTick(() => {
      this.reload()
    })
  },
}
</script>
