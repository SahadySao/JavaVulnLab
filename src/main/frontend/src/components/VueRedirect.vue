<template>
  <button @click="back">Retour</button>
</template>
<script>
export default {
  methods: {
    back() {
      const target = new URLSearchParams(window.location.search).get('returnUrl');
      window.location.href = target;
    },
  },
};
</script>
