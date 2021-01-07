<template>
  <AsideLayout no-gutter>

    <Box style="margin: 8px;">

      <zpa-row style="flex: none; margin-bottom: -15px;">
        <FAB :icon="getShape.icon" :label="getShape.label" @click.native="clickShape"/>
        <UploadFileOperator :root="root" @success="reload"/>
      </zpa-row>

      <DataList
        v-if="shape"
        ref="Table"
        :index="root !== undefined"
        isHidePage
        :columns="getColumns"
        :query="getQuery"
        @on-selected-change="selectedChange"
        @on-dblclick-row="dblclickRow"
      ></DataList>

      <PapersList
        v-else
        ref="Papers"
        :query="getQuery"
        @on-selected-change="selectedChange"
        @on-dblclick-row="dblclickRow"
      />

    </Box>

    <PageFileAside
      v-if="isFile"
      slot="aside"
      :current="currentLocal"
      field="accessToken"
    />

  </AsideLayout>
</template>

<script>
  import fileLocalColumns from './columns/fileLocalColumns'

  import UploadFileOperator from './operators/UploadFileOperator'

  import PageFileAside from './PageFileAside'

  import {queryDeptLocal, queryDeptVerLocal, queryLocal, queryUserLocal, queryUserVerLocal,} from '@/api/admin/file'

  export default {
  name: 'PageFileLocal',

  data() {
    return {
      fileLocalColumns,

      root: undefined, // 判断调用个人或部门接口
      folder: false, // 在root基础上判断调用普通文件夹（false）或版本文件夹（true）接口
      site: '', // 用于拼接地址栏后的路径
      path: '', // 用于拼接接口后的路径
      shape: false,
      columns: [
        {
          title: '类型',
          key: 'fileName',
          sortable: 'custom',
          align: 'center',
          minWidth: 110,
        },
      ],
      currentLocal: undefined,
    }
  },

  components: {
    UploadFileOperator,
    PageFileAside,
  },

  computed: {
    getColumns() {
      return this.root === undefined ? this.columns : this.fileLocalColumns
    },

    getQuery() {
      if (this.root === undefined) {
        return queryLocal
      }

      if (this.root === '') {
        return !this.folder ? queryUserLocal : queryUserVerLocal
      } else {
        return !this.folder ? queryDeptLocal : queryDeptVerLocal
      }
    },

    getShape() {
      return {
        icon: this.shape ? 'ios-apps' : 'ios-list',
        label: this.shape ? '图形' : '列表',
      }
    },

    isFile() {
      return this.currentLocal &&
        this.currentLocal.fileType === 'f'
    },
  },

  methods: {
    reload() {
      this.$nextTick(() => {
        const params = {
          path: this.path
        }

        this.shape ? this.$refs['Table'].load({params}) : this.$refs['Papers'].load({params})
      })
    },

    clickShape() {
      this.shape = !this.shape
      this.reload()
    },

    selectedChange(data) {
      this.currentLocal = data
    },

    dblclickRow(data) {
      const {
        root,
        fileName,
        versions,
      } = data

      if (this.isFile) {
        if (versions === 1) {
          return false
        } else if (versions > 1) {
          this.folder = true
        }
      }

      if (root !== undefined) {
        this.root = root
        this.site = fileName
      } else {
        this.site += `_${fileName}`
      }

      this.$router.push({
        path: this.$route.path,
        query: {
          site: this.site,
        }
      })
    },
  },

  mounted() {
    this.reload()
    this.$root.$on('reload', this.reload)
  },

  watch: {
    '$route'(now, old) {
      if (now.query.site) {
        const res = now.query.site.split('_')

        if (now.query.noFolder) {
          this.folder = false
          delete now.query.noFolder
        }

        this.site = now.query.site

        this.path = this.root
        if (res.length > 1) {
          this.path += res.filter((d, i) => i > 0).join('/')
        }

        this.reload()
      }
    }
  },
}
</script>
