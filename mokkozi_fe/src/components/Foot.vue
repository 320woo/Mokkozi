<template>
  <v-bottom-navigation
    color="white"
    grow
    class="footer justify-space-around"
    background-color="#FFB4B4"
  >
    <router-link to="/home" style="text-decoration: none; color:white">
      <v-btn dark
                  dense
                  color="white"
                  text="text"
                  rounded="rounded"
                  class="my-2">Home
        <v-icon>mdi-history</v-icon>
    </v-btn>
    </router-link>

    <router-link v-if="this.$store.state.jwt === ''" to="/login" style="text-decoration: none; color:white">
      <v-btn dark
                  dense
                  color="white"
                  text="text"
                  rounded="rounded"
                  class="my-2">Login
        <v-icon>mdi-heart</v-icon>
      </v-btn>
    </router-link>

    <router-link v-else to="" style="text-decoration: none; color:white">
      <v-btn dark
                  style="text-decoration: none; color:white"
                  dense
                  color="white"
                  text="text"
                  rounded="rounded"
                  @click="logout()"
                  class="my-2">Logout
        <v-icon>mdi-heart</v-icon>
      </v-btn>
    </router-link>

    <router-link v-if="this.$store.state.jwt !== ''" :to="{ name: 'Profile', params: { userEmail: email }}" style="text-decoration: none; color:white">
      <v-btn dark
                  dense
                  color="white"
                  text="text"
                  rounded="rounded"
                  class="my-2">
        <span>Mypage</span>
        <v-icon>mdi-map-marker</v-icon>
      </v-btn>
    </router-link>
  </v-bottom-navigation>
</template>
<script>
export default {
  name: 'Foot',
  data: () => ({
  }),
  methods: {
    logout() {
      this.$store.dispatch("setJwt", '')
    },
  },
  computed: {
    email() {
      const email = this.$store.state.user.email
      return email
    }
  }
}
</script>
<style scoped>
.footer {
    position: fixed;
    top: auto;
    bottom: 0px;
    left: auto;
    width: 700px;
    z-index: 10;
    elevation: level;
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
