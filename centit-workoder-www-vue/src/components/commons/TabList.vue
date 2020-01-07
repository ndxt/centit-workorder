<template>
  <Tabs
    :class="['zpa-column', 'zpa-tabs', hasDataList && 'dataTabs']"
    @on-click="click"
    :style="style"
  >
    <TabPane
      v-for="(tab, index) in tabData"
      :key="index"
      :label="tab.name"
      :disabled="tab.disabled"
    >
      <slot :name="tab.component"></slot>
    </TabPane>
  </Tabs>
</template>
<script>
export default {
  name: 'TabList',

  props: {
    hasDataList: Boolean,
    tabData: Array,
    height: String,
    noFlex: Boolean,
  },

  computed: {
    style() {
      if (!this.noFlex) {
        return {
          height: `${this.height}px`,
          overflow: 'hidden',
        }
      }
      return {
        height: `${this.height}px`,
        overflow: 'hidden',
        flex: 'none',
      }
    }
  },

  methods: {
    click(index) {
      this.$emit('click', index)
    },
  },
}
</script>
<style lang="less">
  .ivu-tabs {
    display: flex;
    flex-direction: column;

    .ivu-tabs-bar {
      margin-bottom: 8px;
    }

    .ivu-tabs-content {
      flex: 1;
    }

    .ivu-tabs-tabpane {
      display: flex;
      flex-direction: column;
    }
  }

  .ivu-table-fixed-right {
    height: 100% !important;
  }

  .dataTabs {
  .ivu-tabs-content, .ivu-table-wrapper {
    flex: 1 !important;
  }
  .ivu-table-body {
    min-height: calc(100% - 41px);
  }
  }
</style>
