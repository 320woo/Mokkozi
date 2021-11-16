<template>
  <v-container class="board-container">
    <div>
      <v-card max-height="50rem">
        <div>
          <!-- 배경 이미지 부분 -->
          <div v-for="(item, index) in recommends" :key="index">{{ item }}</div>
          <!-- 사용자 프로필 이미지 부분 -->
        </div>
      </v-card>
    </div>
  </v-container>
</template>
<script>
import axios from "axios";
import defaultImage from "../assets/images/white.png";

export default {
  name: "Home",
  components: {},
  created() {
    this.recommend();
  },
  data() {
    return {
      defaultImage: defaultImage,
      recommends: [],
    };
  },
  methods: {
    recommend() {
      axios({
        url: "http://localhost:8000/api/meet/user/recommend/random",
        method: "GET",
        headers: {
          Authorization: "Bearer " + this.$store.state.jwt,
        },
      }).then((resp) => {
        this.recommends = resp.data.random;
        console.log("회원조회 가즈아: ", resp);
      });
    },
  },
};
</script>
