<template>
    <v-footer class="top" color="#FFB4B4" padless="padless" elevation="4">
        <v-row justify="center justify-space-around" no-gutters="no-gutters">
            <v-btn
                dark
                dense
                color="white"
                text="text"
                rounded="rounded"
                class="my-2 btn">
            <router-link to="/board" style="text-decoration: none; color:white">게시판</router-link></v-btn>
            <v-btn
                dark
                dense
                color="white"
                text="text"
                rounded="rounded"
                class="my-2 btn">
            <router-link to="/matching" style="text-decoration: none; color:white">랜덤미팅</router-link></v-btn>
            <v-btn
                dark
                dense
                color="white"
                text="text"
                rounded="rounded"
                class="my-2 btn">
            <router-link to="/" class="mt-4" style="text-decoration: none; color:white">
              <p v-if="isLogin">로그아웃</p>
              <p v-else></p>
            </router-link></v-btn>
        </v-row>
    </v-footer>
</template>
<script>
export default {
  data: () => ({
  }),
  computed: {
    isLogin () {
      const isLogin = this.$store.state.jwt
      return isLogin
    }
  }
}
</script>

<style>
.top {
    position: fixed;
    left: auto;
    top: 0;
    width: 600px;
    z-index: 999;
    }
</style>
