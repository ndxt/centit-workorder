<template>
  <hot-table :settings="hotSettings"></hot-table>
</template>

<script>
import { HotTable } from '@handsontable/vue';
import Handsontable from 'handsontable'

export default {
  data: function() {
    return {
      hotSettings: {
        data: Handsontable.helper.createSpreadsheetData(100, 50),
        colWidths: 100,
        width: '100%',
        height: 320,
        rowHeaders: true,
        colHeaders: true,
        fixedRowsTop: 2,
        fixedColumnsLeft: 2,
        manualColumnResize: true,
        manualRowResize: true,
        headerTooltips: {
          rows: false,
          columns: true,
          onlyTrimmed: true
        }
      },

      contextMenu: true,
    };
  },
  components: {
    HotTable
  }
}
</script>

<style src="../../../node_modules/handsontable/dist/handsontable.full.css"></style>
