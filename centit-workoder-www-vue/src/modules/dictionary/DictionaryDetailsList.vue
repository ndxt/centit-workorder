<template>
  <DataList
    ref="Table"
    :columns="columns"
    :query="query"
    isHidePage
    :operWidth="128"
  >
    <template slot="fab">
      <AddDictionaryDetailOperator
        @success="add"
        :fields="fields"
        :catalogCode="value.catalogCode"
      />
    </template>
    <template slot-scope="props">
      <EditDictionaryDetailOperator
        v-model="props.row"
        :fields="fields"
        @success="row => edit(row, props.index)"
      />
      <RemoveDictionaryDetailOperator
        v-model="props.row"
        @success="remove(props.index)"
      />
    </template>
    <template slot="dataTag" slot-scope="{value}">
      {{value | yesOrNo}}
    </template>
    <template slot="dataStyle" slot-scope="{value}">
      {{CatalogStyle[value] || value}}
    </template>
  </DataList>
</template>

<script>
import AddDictionaryDetailOperator from './operators/AddDictionaryDetailOperator'
import EditDictionaryDetailOperator from './operators/EditDictionaryDetailOperator'
import RemoveDictionaryDetailOperator from './operators/RemoveDictionaryDetailOperator'

export default {
  name: 'DictionaryDetailsList',

  components: {
    AddDictionaryDetailOperator,
    EditDictionaryDetailOperator,
    RemoveDictionaryDetailOperator,
  },

  data() {
    return {
      CatalogStyle:
          {
            'F': '框架固有',
            'G': '国标',
            'S': '系统参数',
            'U': '用户参数'
          }
      ,
    }
  },

  props: {
    value: Object,
  },

  provide() {
    return {
      detail: this,
    }
  },

  computed: {
    fields() {
      if (!this.value || !this.value.fieldDesc) return {}

      return JSON.parse(this.value.fieldDesc.replace(/\\/g, ''))
    },

    /**
       * 根据字段描述字段生成columns
       * @returns {Array}
       */
    columns() {
      Object.keys(this.fields)
        .filter(k => this.fields[k].isUse === 'F')
        .forEach(key => {
          delete this.fields[key]
        })

      const fields = this.fields
      const columns = [
        {
          key: 'dataOrder',
          title: '排序',
          sortable: true,
          align: 'center',
          width: 80,
        }
      ]

      for (let key of Object.keys(fields)) {
        const obj = fields[key] || {}
        const title = obj.value || key

        columns.push({
          key,
          title,
          sortable: true,
          ellipsis: true,
          tooltip: true,
          minWidth: 110,
        })
      }

      columns.push({
        key: 'dataStyle',
        title: '类型',
        sortable: true,
        align: 'center',
        minWidth: 100,
      })

      return columns
    },

    data() {
      return this.$refs['Table'].data
    },
  },

  methods: {
    query() {
      return this.value ? (this.value.dataDictionaries || []) : []
    },

    reload() {
      this.$refs['Table'].load()
      this.$refs['Table'].resize()
    },

    add(row) {
      this.value.dataDictionaries.push(row)
      this.reload()
    },

    edit(row, index) {
      this.$set(this.value.dataDictionaries, index, row)
      this.reload()
    },

    remove(index) {
      this.value.dataDictionaries.splice(index, 1)
      this.reload()
    },
  },

  mounted() {
    this.reload()
  },

  watch: {
    value() {
      this.reload()
    },
  },
}
</script>
