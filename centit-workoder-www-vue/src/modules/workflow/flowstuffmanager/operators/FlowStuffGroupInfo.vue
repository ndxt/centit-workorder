<template>
  <zpa-form-group>
    <Card style="width: 300px" @click.native="cardClicked()">
      <p slot="title">
        {{ value.groupName }}
      </p>
      <div slot="extra">
        <Button type="text">编辑</Button>
        <Button type="text">删除</Button>
      </div>
      <zpa-text-input
        v-for="stuffType in value.stuffTypes"
        :key="stuffType.stuffTypeId"
        v-model="stuffType.stuffTypeName"
        readonly
      />
    </Card>
  </zpa-form-group>
</template>

<script>
export default {
  name: 'FlowStuffGroupInfo',

  props: {
    value: Object,
  },

  methods: {
    cardClicked() {
      console.log('selectGroup: ' + this.data.groupId)
      this.$emit('selectGroup', this.data.groupId)
    },
  },
}
</script>
