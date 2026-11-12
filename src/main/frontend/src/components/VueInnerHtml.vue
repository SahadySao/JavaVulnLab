<template>
  <div ref="box"></div>
</template>
<script>
export default {
  props: ['message'],
  mounted() {
    this.$refs.box.innerHTML = this.message;
  },
};
</script>
