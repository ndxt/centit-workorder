<template>
  <ModalOperator :key='key' :icon='icon' :label='label' fab title="新增文档">
    <zpa-form ref="Form">
      <zpa-form-group>
        <zpa-text-input label="标题" v-model="docVal.docTitle" required />
      </zpa-form-group>
    </zpa-form>
  </ModalOperator>
</template>
<script>
import ModalOperatorMixin from '@/components/commons/ModalOperatorMixin';
export default {
  name: 'Addknowedge',
  data() {
    return {
      key: 0,
    };
  },
  inject: ['top'],
  props: {
    docVal: {
      type: Object,
      default: function() {
        return {}
      }
    },
    label: String,
    icon: String
  },
  mixins: [ModalOperatorMixin],
  mounted() {},
  methods: {
    beforeSubmit() {
      console.log(this.docVal)
      return this.$refs['Form'].validate();
    },

    onSubmit() {
      this.$emit('onSumbit', {
        ...this.docVal
      });
      // this.docVal.docTitle = '';
    },
    onCancel() {
      // this.docVal.docTitle = '';
    }
  }
};
</script>
