<template>
  <v-container class="board-container">
    <div>
      <v-card max-height="50rem">
        <div>
          <!-- 배경 이미지 부분 -->
          <div v-for="(item, index) in recommends" :key="index">
            <v-card style="width: 700px; height: 200px">
              <v-layout
                ><v-card-text style="border-bottom: 1px solid gainsboro">
                  <v-row>
                    <div>
                      <img width="200px" height="200px" :src="item.profile" />
                      {{ item.nickname }}
                    </div>
                  </v-row>
                </v-card-text></v-layout
              >
            </v-card>
          </div>
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
    if (this.$store.state.jwt != undefined) {
      this.my_recommend();
    } else this.guest_recommend();
  },
  data() {
    return {
      defaultImage: defaultImage,
      recommends: [],
    };
  },
  methods: {
    my_recommend() {
      axios({
        url: "http://localhost:8000/api/meet/user/recommend/random",
        method: "GET",
        headers: {
          Authorization: "Bearer " + this.$store.state.jwt,
        },
      }).then((resp) => {
        this.recommends = resp.data.random;
        console.log("나의 회원조회 가즈아: ", resp);
      });
    },
    guest_recommend() {
      axios({
        url: "http://localhost:8000/api/meet/user/recommend/random",
        method: "GET",
      }).then((resp) => {
        this.recommends = resp.data.random;
        console.log("게스트 회원조회 가즈아: ", resp);
      });
    },
  },
};
</script>
