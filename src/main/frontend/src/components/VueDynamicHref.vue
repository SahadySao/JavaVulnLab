<template>
  <!-- URL entière venant du profil : javascript:alert(1) passe -->
  <a :href="profile.website">Site web</a>
</template>
<script>
export default { props: ['profile'] };
</script>
