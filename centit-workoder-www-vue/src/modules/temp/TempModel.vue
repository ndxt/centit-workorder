<template>
  <iframe style="width: 100%; height: 100%;" :src="src" frameborder="0"></iframe>
</template>

<script>
export default {
  name: 'GzModel',
  computed: {
    src() {
      const id = this.$route.params.id;
      let type = 'listPreview';
      if (this.$route.query.type === 'calendar') {
        type = 'calendarPreview';
      } else if (this.$route.query.type === 'card') {
        type = 'cardPreview';
      }
      return `http://ceshi.centit.com/stat/#/${type}/${id}`;
    }
  }
};
</script>

<style scoped>
</style>
