<template>
  <v-bottom-navigation
    color="white"
    grow
    class="footer justify-space-around"
    background-color="#FFB4B4"
  >
    <router-link to="/" style="text-decoration: none; color:white">
      <v-btn dark
                  dense
                  color="white"
                  text="text"
                  rounded="rounded"
                  class="my-2">Home
        <v-icon>mdi-history</v-icon>
    </v-btn>
    </router-link>

    <router-link to="/login" style="text-decoration: none; color:white">
    <v-btn dark
                dense
                color="white"
                text="text"
                rounded="rounded"
                class="my-2">Login
      <v-icon>mdi-heart</v-icon>
    </v-btn>
    </router-link>

    <router-link to="/login" style="text-decoration: none; color:white">
    <v-btn dark
                dense
                color="white"
                text="text"
                rounded="rounded"
                class="my-2">
      <span>FAQ</span>
      <v-icon>mdi-map-marker</v-icon>
    </v-btn>
    </router-link>
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
    width: 600px;
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
