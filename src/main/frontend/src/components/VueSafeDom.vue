<template>
  <div ref="box"></div>
</template>
<script>
export default {
  props: ['message'],
  mounted() {
    this.$refs.box.textContent = this.message;
    this.$refs.box.innerHTML = '';
    setTimeout(() => this.$emit('ready'), 100);
    window.location.href = '/accueil';
  },
};
</script>
