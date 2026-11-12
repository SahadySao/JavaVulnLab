<template>
  <!-- v-html : le commentaire est inséré comme du HTML -->
  <div class="comment" v-html="comment.body"></div>
</template>
<script>
export default {
  props: ['comment'],
};
</script>
