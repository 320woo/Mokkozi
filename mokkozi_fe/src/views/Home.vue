<template>
  <v-container>
    <div class="mt-7">
      <v-carousel
        cycle
        height="250"
        width="700px;"
        hide-delimiter-background
        show-arrows-on-hover
      >
        <template v-slot:prev="{ on, attrs }">
          <v-btn color="success" v-bind="attrs" v-on="on">Previous slide</v-btn>
        </template>
        <template v-slot:next="{ on, attrs }">
          <v-btn color="info" v-bind="attrs" v-on="on">Next slide</v-btn>
        </template>
        <v-carousel-item v-for="(slide, i) in slides" :key="i">
          <v-sheet :color="colors[i]" height="100%">
            <v-row class="fill-height" align="center" justify="center">
              <div class="text-h2"><img src="slide" /></div>
            </v-row>
          </v-sheet>
        </v-carousel-item>
      </v-carousel>
    </div>
    <div>
      <!-- 배경 이미지 부분 -->
      <v-row style="text-align: center">
        <v-col v-for="(item, index) in recommends" :key="index" colos="6">
          <v-card class="ml-2 my-1" max-width="300">
            <v-img height="180" width="280" :src="item.profile"></v-img>

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
                  style="color: white"
                  color="#FFB4B4"
                  class="mt-3 ml-2 mr-2"
                  small
                  >프로필</v-btn
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
    </div>
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
      // this.my_recommend();
      // this.connect()
    } // else this.guest_recommend();
  },
  data() {
    return {
      recvList: [], // 받은 메세지
      connected: false,
      defaultImage: defaultImage,
      recommends: [],
      address: [],
      colors: [
        "indigo",
        "warning",
        "pink darken-2",
        "red lighten-1",
        "deep-purple accent-4",
      ],
      slides: [
        "../assets/images/데이트1.png",
        "../assets/images/데이트2.png",
        "../assets/images/데이트3.png",
        "../assets/images/데이트4.png",
        "../assets/images/데이트5.png",
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
        url: process.env.VUE_APP_API_URL + "/api/meet/user/recommend/guest_random",
        method: "GET",
      }).then((resp) => {
        this.recommends = resp.data.random;
        var result = [];

        for (var i = 0; i < resp.data.random.length; i++) {
          result[i] = resp.data.random[i].address.split(" ");
          this.recommends[i].address = result[i][0] + " " + result[i][1];
        }

        console.log("게스트 회원조회 가즈아: ", resp);
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
