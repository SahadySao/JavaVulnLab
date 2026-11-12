<template>
  <!-- {{ }} échappe : sûr -->
  <p>{{ comment.body }}</p>
</template>
<script>
export default { props: ['comment'] };
</script>
