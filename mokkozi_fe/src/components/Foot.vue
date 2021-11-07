<template>
      <v-bottom-navigation
    :value="value"
    color="white"
    grow
    class="footer"
    background-color="#FFB4B4"
  >
    <v-btn>
      <span>Home</span>
      <v-icon>mdi-history</v-icon>
    </v-btn>

    <v-btn>
      <span>Login</span>
      <v-icon>mdi-heart</v-icon>
    </v-btn>

    <v-btn>
      <span>FAQ</span>
      <v-icon>mdi-map-marker</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>
<script>
export default {
  name: 'Foot',
  data: () => ({
  })
}
</script>
<style scoped>
.footer {
    position: fixed;
    top: auto;
    bottom: 0px;
    left: auto;
    width: 37rem;
}

.footer-text {
  text-decoration: none;
  color: white;
  font-weight: bold;
  margin-left : 0px;
}

.footer-btn {
  margin-left: 60px;
}
</style>
