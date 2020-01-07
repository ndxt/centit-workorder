<template>
  <AsideLayout no-gutter>

    <SearchLayout>

      <SearchBox slot="search" @search="reload" @reset="reload">
        <zpa-text-input label="机构名" v-model="params.unitName"/>
      </SearchBox>

      <zpa-column>
        <TreeList
          ref="Table"
          index
          :columns="unitColumns"
          :query="dept ? querySubUnit : queryUnit"
          id="unitCode"
          treeColumnField="unitName"
          @on-selected-change="changeSelected"
          @treeData="checkListLength"
        >
          <template slot="fab" v-if="!dept">
            <AddUnitOperator @success="reload"/>
          </template>
          <template slot-scope="props">
            <AddNextUnitOperator v-model="props.row" @success="reload"/>
            <EditUnitOperator v-model="props.row" @success="reload"/>
            <UnitPowerOperator v-model="props.row"/>
            <RemoverUnitOperator v-model="props.row" @success="reload"/>
          </template>
        </TreeList>
      </zpa-column>

    </SearchLayout>

    <RightUnit slot="aside" v-model="data" />

  </AsideLayout>
</template>

<script>
import unitColumns from './unitColumns'
import AddUnitOperator from './operators/AddUnitOperator'
import AddNextUnitOperator from './operators/AddNextUnitOperator'
import EditUnitOperator from './operators/EditUnitOperator'
import UnitPowerOperator from './operators/UnitPowerOperator'
import RemoverUnitOperator from './operators/RemoverUnitOperator'

import RightUnit from './RightUnit'

import {
  queryUnit,
  querySubUnit
} from '@/api/admin/unit'

export default {
  name: 'PageUnit',

  components: {
    AddUnitOperator,
    AddNextUnitOperator,
    EditUnitOperator,
    UnitPowerOperator,
    RemoverUnitOperator,
    RightUnit,
  },

  props: {
    dept: Boolean,
  },

  provide() {
    return {
      top: this
    }
  },

  data() {
    return {
      unitColumns,
      params: {},
      data: {},
    }
  },

  methods: {
    queryUnit,

    querySubUnit,

    checkListLength(data) {
      if (data.length < 1) {
        this.data = {}
      }
    },

    reload() {
      const params = this.params
      return this.$refs['Table'].load({params})
    },
    changeSelected(row) {
      this.data = row
    },
  },

  mounted() {
    this.$nextTick(() => {
      this.reload()
    })
  },
}
</script>
