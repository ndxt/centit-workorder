<template>
  <SearchLayout>

    <div style="clear:both;">
      <div style="float:left;width:36%;padding: 10px;">

        <knowedgeTree />
      </div>
      <knowedgeDoc style="float:left;padding:10px;"></knowedgeDoc>
    </div>
  </SearchLayout>
</template>
<script>
//  :show-checkbox="checkbox"
//       :multiple="multiple"
//       @on-check-change="checkChange"
//       @on-select-change="selectChange"

import knowedgeDoc from './KnowedgeDoc';
import knowedgeTree from './KnowedgeTree';
import { knowedgeSeach } from '@/api/knowedge/knowedge';
export default {
  name: 'PageKnowedgeBase',
  data() {
    return {
      docData: [],
      params: {},
      docVal: {}
    };
  },
  components: {
    knowedgeDoc,
    knowedgeTree
  },
  provide() {
    return {
      top: this
    };
  },

  mounted() {},
  methods: {
    open() {
      this.modalOpen = true;
    },
    search() {
      knowedgeSeach(this.params);
    }
  }
};
</script>
