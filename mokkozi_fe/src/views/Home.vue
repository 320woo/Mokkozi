<template>
  <v-container fluid style="width: 600px; height: 700px">
    <div class="mt-3"></div>

    <!-- 배경 이미지 부분 -->
    <v-row style="text-align: center">
      <v-col v-for="(item, index) in recommends" :key="index" colos="6">
        <v-card class="ml-2 my-1" max-width="260">
          <v-img height="180" width="260" :src="item.profile"></v-img>

          <v-card-title
            >{{ item.nickname
            }}<span
              class="ml-2 mt-1"
              style="font-size: 12px; font-weight: normal"
              >{{ item.address }}</span
            ></v-card-title
          >

          <v-card-text>
            <v-row align="center"> </v-row>
            <div>
              <v-btn
                @click="go_profile(item.email)"
                style="color: white"
                color="#FFB4B4"
                class="mt-3 ml-2 mr-2"
                small
                >프로필 가기</v-btn
              ><v-btn
                style="color: white"
                color="#FFB4B4"
                class="mt-3 ml-2 mr-2"
                small
                >미팅신청</v-btn
              >
            </div>
          </v-card-text>

          <v-divider class="mx-4"></v-divider>
        </v-card>
      </v-col>
    </v-row>
    <!-- 사용자 프로필 이미지 부분 -->
  </v-container>
</template>
<script>
import axios from "axios";
import defaultImage from "../assets/images/white.png";
// import Stomp from 'webstomp-client'
// import SockJS from 'sockjs-client'

export default {
  name: "Home",
  components: {},

  created() {
    if (this.$store.state.jwt !== "") {
      this.my_recommend();
      // this.connect()
    } else this.guest_recommend();
  },
  data() {
    return {
      recvList: [], // 받은 메세지
      connected: false,
      defaultImage: defaultImage,
      recommends: [],
      address: [],
      items: [
        {
          src: "@/assets/images/커버.png",
        },
        {
          src: "@/assets/images/데이트2.png",
        },
        {
          src: "@/assets/images/데이트3.png",
        },
        {
          src: "@/assets/images/데이트4.png",
        },
        {
          src: "@/assets/images/데이트5.png",
        },
      ],
    };
  },
  methods: {
    // connect () {
    //   // SockJs를 생성한다.
    //   let socket = new SockJS(process.env.VUE_APP_API_URL + "/ws-stomp")
    //   let stompClient = Stomp.over(socket)

    //   console.log(`소켓 연결을 시도합니다. 서버 주소는 ${process.env.VUE_APP_API_URL}`)

    //   stompClient.connect({},
    //     frame => {
    //       // 소켓 연결 성공
    //       this.connected = true
    //       console.log('소켓 연결 성공', frame)
    //       // 서버의 메세지 전송 endPoint를 구독합니다.
    //       // 이런 형태를 pub sub 구조라고 합니다.
    //       stompClient.subscribe('/send', res => {
    //         console.log('구독으로 받은 메세지입니다.', res.body)

    //         // 받은 데이터를 JSON으로 파싱하고 리스트에 넣어줍니다.
    //         this.recvList.push(JSON.parse(res.body))
    //       })
    //     },
    //     error => {
    //       // 소켓 연결 실패
    //       console.log('소켓 연결 실패', error)
    //       this.connected = false
    //     }
    //   )

    // },
    go_profile(userEmail) {
      if (!this.$store.state.jwt) {
        alert("로그인이 필요합니다😀");
        this.$router.push({ name: "Login" });
      } else {
        this.$router.push({
          name: "Profile",
          params: { userEmail: userEmail },
        });
      }
    },
    my_recommend() {
      axios({
        url: process.env.VUE_APP_API_URL + "/api/meet/user/recommend/random",
        method: "GET",
        headers: {
          Authorization: "Bearer " + this.$store.state.jwt,
        },
      }).then((resp) => {
        this.recommends = resp.data.random;
        var result = [];

        for (var i = 0; i < resp.data.random.length; i++) {
          result[i] = resp.data.random[i].address.split(" ");
          this.recommends[i].address = result[i][0] + " " + result[i][1];
        }

        console.log("나의 회원조회 가즈아: ", resp);
      });
    },
    guest_recommend() {
      axios({
        url:
          process.env.VUE_APP_API_URL + "/api/meet/user/recommend/guest_random",
        method: "GET",
      }).then((resp) => {
        console.log("비로그인 : 추천 목록 가져옵니다.", resp);

        this.recommends = resp.data.random;
        var result = [];

        for (let i = 0; i < resp.data.random.length; i++) {
          result[i] = resp.data.random[i].address.split(" ");
          this.recommends[i].address = result[i][0] + " " + result[i][1];
        }
      });
    },
  },
};
</script>
<style scoped>
.board-container {
  overflow-y: scroll;
}
</style>
