<template>
  <div class="comment" v-html="safeBody"></div>
</template>
<script>
import DOMPurify from 'dompurify';
export default {
  props: ['comment'],
  computed: {
    safeBody() {
      return DOMPurify.sanitize(this.comment.body);
    },
  },
};
</script>
