<template>
  <zpa-column height="auto" :id="id">
    <h3>{{title}}</h3>
    <p>
      &nbsp;&nbsp;默认Label显示位置在表单元素左侧，可以给 <b>Form</b> 或者 <b>FormGroup</b> 组件设置 <b>LabelAlign</b> 和 <b>LabelWidth</b> 属性修改其下的 Label 位置和宽度
    </p>
    <p>
      &nbsp;&nbsp;单个表单元素也可以设置 <b>LabelAlign</b> 和 <b>LabelWidth</b> 属性达到特殊布局
    </p>
    <zpa-row height="auto">
      <zpa-column>
        <zpa-form>
          <zpa-form-group>
            <zpa-text-input label="Label Top" labelAlign="top" />
            <zpa-text-input label="默认宽度" />
            <zpa-text-input label="Label宽度：128px" :labelWidth="128" />
          </zpa-form-group>
        </zpa-form>
      </zpa-column>

      <zpa-column>
        <codemirror v-model="code"></codemirror>
      </zpa-column>
    </zpa-row>
    <br>
  </zpa-column>
</template>

<script>
import demo from './demo'

export default {
  name: 'FormLabelDemo',

  mixins: [demo],

  inject: ['main'],

  data() {
    return {
      id: 'FormLabelDemo',

      title: '标题设置',

      code:
      `
<!-- 表单元素继承Form -->
<zpa-form labelAlign="top" :labelWidth="128"></zpa-form>
<!-- 表单元素继承FormGroup -->
<zpa-form>
  <zpa-form-group labelAlign="top" :labelWidth="128"></zpa-form-group>
  <zpa-form-group labelAlign="left" :labelWidth="256"></zpa-form-group>
</zpa-form>
<!-- 单独设置 -->
<zpa-text-input label="Label Top" labelAlign="top" />
<zpa-text-input label="默认宽度" />
<zpa-text-input label="Label宽度：128px" :labelWidth="128" />
      `
    }
  },
}
</script>
