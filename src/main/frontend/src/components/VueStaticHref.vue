<template>
  <!-- L'URL commence par une partie fixe : pas de javascript: possible -->
  <a :href="`/factures/${invoice.id}`">Voir</a>
  <img :src="'/img/' + invoice.logo">
</template>
<script>
export default { props: ['invoice'] };
</script>
