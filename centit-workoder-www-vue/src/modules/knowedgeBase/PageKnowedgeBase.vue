<template>
  <SearchLayout>

    <div>
      <div class="konwedgeTreeBox" >
        <knowedgeTree @getDoc='getDocFun' />
      </div>
      <knowedgeDoc class="knowedgeDoc" style="" v-model="docObject"></knowedgeDoc>
    </div>
  </SearchLayout>
</template>
<script>
import knowedgeDoc from './KnowedgeDoc';
import knowedgeTree from './KnowedgeTree';
import { knowedgeSeach, getDoc } from '@/api/knowedge/knowedge';
export default {
  name: 'PageKnowedgeBase',
  data() {
    return {
      params: {},
      docObject: {}
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
    async getDocFun(params) {
      this.docObject = await getDoc(params);
      console.log(params, this.docObject);
    },
    search() {
      knowedgeSeach(this.params);
    }
  }
};
</script>
<style scoped>
.konwedgeTreeBox {
  width: calc(30% - 40px);
  padding: 20px;
  border: 1px dashed #ddd;
  margin: 20px;
  height: calc(100% - 40px);
  position: absolute;
}
.knowedgeDoc {
  position: absolute;
  left: 30%;
  top: 0;
  right: 0;
  bottom: 0;
  padding: 10px;
}
</style>
