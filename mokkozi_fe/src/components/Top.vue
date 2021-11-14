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
            
              <p class="mt-4" v-if="this.$store.state.user.show" @click="logout">로그아웃</p>
              <p v-else class="mt-4"><router-link to="/profile" style="text-decoration: none; color:white">나의 페이지</router-link></p></v-btn>
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
  },
  methods: {
    logout () {
      this.$store.dispatch("logout");
    }
  },
}
</script>

<style>
.top {
    position: fixed;
    left: auto;
    top: 0;
    width: 700px;
    z-index: 10;
    }
</style>
