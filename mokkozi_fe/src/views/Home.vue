<template>
  <v-container fluid style="width: 600px; height: 700px;" class="board-container">
    <div class="mt-3">
      <v-carousel
        cycle
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

<div class="review" style="width:500px;">
<div class="wrap" >
    <div class="mt-10 reviews" style="text-align: center ">
      <img :src="this.reviewImg" style="width:123px;height:36px; margin-top:30px;">
      <h1>모꼬지 유저들의 리얼 후기</h1>
      <div class="font02" style="width:80%; margin:0 auto;margin-bottom:30px;">모꼬지에서 인연을 연인으로 만든 실제 사용자분들의<br>진솔한 리뷰를 만나보세요!</div>
    </div>
</div>
<div class="wrap mb-10">
    <v-slide-group
      v-model="model"
      class="pa-4"
      show-arrows
    >
      <v-slide-item
        v-for="n in this.reviews"
        :key="n"
      >
        <v-card
          class="ma-4"
          height="200"
          color="#FF9292"
          width="340"
        >
        <v-card-text class="white">
          <div class="my-4">
            {{n.content}}
          </div>
        </v-card-text>

          <v-divider class="mx-4"></v-divider>

            <v-card-actions>
      <v-list-item class="grow">
        <v-list-item-avatar color="pink darken-3">
          <v-img
            class="elevation-6"
            alt=""
            :src="n.avatar"
          ></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>{{n.nickName}}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-card-actions>
        </v-card>
      </v-slide-item>
    </v-slide-group>
  </v-sheet>

</div>
</div>
  </v-container>
</template>
<script>
import axios from "axios";
import defaultImage from "../assets/images/white.png";
// import Stomp from 'webstomp-client'
// import SockJS from 'sockjs-client'
import reviewImg from '../assets/images/reviews.png'
import male from '../assets/images/male.svg'
import female from '../assets/images/female.svg'

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
      reviewImg : reviewImg,
      reviews:[
        {nickName:"OggGirr",
        content:" 소개팅 서비스라고 해서 반신반의했는데 덕분에 여기 통해서 지금 남자친구와 만났어요! 아기자기하고 깔끔해서 사용할 때 더 만족했던 것 같아요!",
        avatar : female
        },
        {
          nickName:"팡교",
          content : "꽤 괜찮은 웹! 원하는 상대를 찾을 수 있는 것도 마음에 들고 무엇보다 추천 기능을 통해 새로운 사람들을 만나면서 얻는 설렘도 좋습니다!",
          avatar : male
        },
        {
          nickName:"bird1",
          content : "저는 모꼬지 앱 최고의 수혜자라고 할 수 있습니다. 부족한 저에게 무척 과분한 지금의 여자친구를 모꼬지를 통해서 만나 3년째 만나고 있어요.",
          avatar : male
        },
        {
          nickName:"꾸뛰르",
          content : "작동법이 정말로 쉽습니다! 저는 기계치임에도 불구하고 손쉽게 사용할 수 있을 만큼 사용법이 간단했습니다. 깔끔하고 매우 관리가 잘 되었다는 느낌을 받았습니다. ",
          avatar : female
        }
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
      }).then(resp => {
        console.log("비로그인 : 추천 목록 가져옵니다.", resp)

        // this.recommends = resp.data.random;
        // const result = [];

        // for (let i = 0; i < resp.data.random.length; i++) {
        //   result[i] = resp.data.random[i].address.split(" ");
        //   this.recommends[i].address = result[i][0] + " " + result[i][1];
        // }

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
