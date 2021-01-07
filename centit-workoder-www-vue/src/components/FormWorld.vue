<template>
  <zpa-row :z-index="1" background="#fff" style="overflow: auto;">
    <zpa-column width="100px">
      <Anchor
        v-if="demos"
        show-ink
        style="position: fixed;"
        @on-change="onChange"
      >
        <AnchorLink
          v-for="d of demos"
          :key="d.id"
          :href="`#${d.id}`" :title="d.title" />
      </Anchor>
    </zpa-column>
    <zpa-column>
      <FormMainDemo />

      <FormBasicDemo />

      <FormBasicBakDemo />

      <FormAsyncDemo />

      <FormAdminDemo />

      <FormLayoutDemo />

      <FormLabelDemo />

      <FormValidatorDemo />

      <FormValidatorAdvanceDemo />
    </zpa-column>
  </zpa-row>
</template>

<script>
  import Vue from 'vue'
  import VueCodemirror from 'vue-codemirror'
  import 'codemirror/lib/codemirror.css'
  import 'codemirror/mode/htmlmixed/htmlmixed.js'

  import FormMainDemo from './demos/FormMain'
  import FormBasicDemo from './demos/FormBasic'
  import FormBasicBakDemo from './demos/FormBasicBak'
  import FormAsyncDemo from './demos/FormAsync'
  import FormAdminDemo from './demos/FormAdmin'
  import FormLayoutDemo from './demos/FormLayout'
  import FormLabelDemo from './demos/FormLabel'
  import FormValidatorDemo from './demos/FormValidator'
  import FormValidatorAdvanceDemo from './demos/FormValidatorAdvance'

  Vue.use(VueCodemirror, {
  options: {
    tabSize: 2,
    mode: 'text/html',
    lineNumbers: true,
  },
})

export default {
  name: 'FormWorld',

  components: {
    FormMainDemo,
    FormBasicDemo,
    FormBasicBakDemo,
    FormAsyncDemo,
    FormAdminDemo,
    FormLayoutDemo,
    FormLabelDemo,
    FormValidatorDemo,
    FormValidatorAdvanceDemo,
  },

  provide() {
    return {
      main: this,
    }
  },

  data() {
    return {
      demos: undefined,
    }
  },

  methods: {
    onChange(o) {
      const node = document.getElementById(o.replace(/#/g, ''))
      if (node) {
        node.scrollIntoView()
      }
    },

    addDemo(id, title) {
      if (!this.demos) this.demos = {}

      this.demos[id] = {
        id,
        title,
      }
    },

    removeDemo(id) {
      delete this.demos[id]
    },
  },
}
</script>
