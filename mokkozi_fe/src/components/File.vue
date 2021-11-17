<template>
  <v-container class="board-container">
    <div>
      <v-card class="board-card" max-height="50rem">
        <div class="profile" style="height: 100%; position: relative">
          <report-user
            :userEmail="this.$route.params.userEmail"
            class="report-icon"
          />
          <!-- 배경 이미지 부분 -->
          <img class="bg-img" :src="defaultImage" alt="커버사진" />
          <!-- 사용자 프로필 이미지 부분 -->
          <div class="user-img-frame" style="overflow: hidden">
            <div class="sub-frame">
              <img
                class="user-img"
                :src="propImage"
                alt=""
                width="100px"
                height="100px"
                style="border-radius: 100px; object-fit: cover"
              />
            </div>
          </div>
          <!-- 사용자 프로필 이미지 수정 버튼 -->
          <div class="user-img-mod-frame">
            <div class="mod-sub-frame">
              <label for="ex_file">
                <img :src="camera" alt="카메라 아이콘"
              /></label>
              <input type="file" id="ex_file" />
            </div>
          </div>
          <div class="user-info">
            <div>{{ this.nickname }}</div>
            <div>{{ this.email }}</div>
          </div>
          <div class="user-follow">
            <v-btn
              @click="follow"
              dark="dark"
              dense="dense"
              color="#FFB4B4"
              class="my-2 font-weight-black"
            >
              팔로우
            </v-btn>
          </div>
          <!-- <div class="user-follow">
                            <v-btn @click="unfollow" dark="dark" dense="dense" color="#FFB4B4" class="my-2 font-weight-black">
                               팔로우 취소
                            </v-btn>
                        </div> -->
          <!-- 인적 사항 부분 -->
          <div>
            <v-card
              style="border-radius: 10px"
              class="mt-16 ml-10 mr-10"
              elevation="2"
            >
              <v-container>
                <v-layout
                  row="row"
                  wrap="wrap"
                  style="text-align: center; color: #ffb4b4"
                >
                  <v-flex>
                    <v-dialog width="365">
                      <template v-slot:activator="{ on, attrs }">
                        <div
                          @click="follower"
                          color="red lighten-2"
                          dark="dark"
                          v-bind="attrs"
                          v-on="on"
                        >
                          <div class="font-weight-black">팔로워</div>
                          <div class="font-weight-medium">{{ num2 }}</div>
                        </div>
                      </template>
                      <v-card>
                        <div class="follow-title">
                          <div>팔로워</div>
                        </div>
                        <div v-for="(item, index) in followers" :key="index">
                          <v-card-text
                            style="border-bottom: 1px solid gainsboro"
                          >
                            <div>
                              <v-avatar size="55px">
                                <img
                                  alt="Avatar"
                                  width="50px"
                                  :src="item.profileUrl"
                                />
                              </v-avatar>
                              {{ item.nickname }}
                            </div>
                          </v-card-text>
                        </div>
                      </v-card>
                    </v-dialog>
                  </v-flex>
                  <v-flex>
                    <v-dialog width="365">
                      <template v-slot:activator="{ on, attrs }">
                        <div
                          @click="following"
                          color="red lighten-2"
                          dark="dark"
                          v-bind="attrs"
                          v-on="on"
                        >
                          <div class="font-weight-black">팔로잉</div>
                          <div class="font-weight-medium">
                            {{ num1 }}
                          </div>
                        </div>
                      </template>
                      <v-card>
                        <div class="follow-title">
                          <div>팔로잉</div>
                        </div>
                        <div v-for="(item, index) in followings" :key="index">
                          <v-card-text
                            style="border-bottom: 1px solid gainsboro"
                          >
                            <div>
                              <v-avatar size="55px">
                                <img
                                  alt="Avatar"
                                  width="50px"
                                  :src="item.profileUrl"
                                />
                              </v-avatar>
                              {{ item.nickname }}
                            </div>
                          </v-card-text>
                        </div>
                      </v-card>
                    </v-dialog>
                  </v-flex>
                </v-layout>
              </v-container>
            </v-card>
            <span style="text-align: left">
              <div class="mt-10 ml-10 mr-10">
                <div>저는 이런 사람입니다</div>
                <div>무교, 비흡연자, 술을 못마심, 내성적</div>
                <div>저는 이런 사람을 만나고 싶어요</div>
                <div>무교, 술을 잘마심, 친절함</div>
              </div>
              <div>
                <v-row>
                  <v-col
                    v-for="n in 9"
                    :key="n"
                    class="d-flex child-flex mt-10"
                    cols="4"
                  >
                    <v-img
                      :src="`https://picsum.photos/500/300?image=${n * 5 + 10}`"
                      :lazy-src="`https://picsum.photos/10/6?image=${
                        n * 5 + 10
                      }`"
                      aspect-ratio="1"
                      class="grey lighten-2"
                    >
                      <template v-slot:placeholder>
                        <v-row
                          class="fill-height ma-0"
                          align="center"
                          justify="center"
                        >
                          <v-progress-circular
                            indeterminate
                            color="grey lighten-5"
                          ></v-progress-circular>
                        </v-row>
                      </template>
                    </v-img>
                  </v-col>
                </v-row>
              </div>
            </span>
            <div></div>
          </div>
        </div>
        <!-- end of 사용자 프로필 -->
      </v-card>
    </div>
  </v-container>
</template>

<script>
import defaultImage from "../assets/images/커버.png";
import camera from "../assets/images/camera.png";
import ReportUser from "./ReportUser";
import axios from "axios";

export default {
  name: "File",
  created() {
    this.getuser();
    this.follower();
    this.following();
  },
  components: {
    ReportUser,
  },
  data: () => ({
    defaultImage: defaultImage,
    propImage: "",
    camera: camera,
    profile: "",
    email: "",
    nickname: "",
    followers: [],
    followings: [],
    num1: "",
    num2: "",
  }),
  methods: {
    getuser() {
      axios({
        url: process.env.VUE_APP_API_URL + "/api/meet/user/getuser",
        method: "GET",
        headers: {
          Authorization: "Bearer " + this.$store.state.jwt,
        },
        params: {
          toUserEmail: this.$route.params.userEmail,
        },
      }).then((resp) => {
        console.log("회원정보 확인: ", resp);
        this.propImage = resp.data.profile;
        this.nickname = resp.data.nickname;
        this.email = resp.data.email;
      });
    },

    follow() {
      console.log(this.$route.params.userEmail);
      axios({
        url: process.env.VUE_APP_API_URL + "/api/meet/user/follow",
        method: "POST",
        headers: {
          Authorization: "Bearer " + this.$store.state.jwt,
        },
        params: {
          toUserEmail: this.$route.params.userEmail,
        },
      }).then((resp) => {
        console.log("팔로우 가즈아: ", resp);
      });
    },

    unfollow() {
      axios({
        url: process.env.VUE_APP_API_URL + "/api/meet/user/unfollow",
        method: "DELETE",
        headers: {
          Authorization: "Bearer " + this.$store.state.jwt,
        },
        params: {
          followId: this.$route.params.userEmail,
        },
      }).then((resp) => {
        console.log("언팔로우 가즈아: ", resp);
      });
    },

    follower() {
      axios({
        url: process.env.VUE_APP_API_URL + "/api/meet/user/followers",
        method: "GET",
        headers: {
          Authorization: "Bearer " + this.$store.state.jwt,
        },
      }).then((resp) => {
        console.log("팔로워 수 : ", resp);
        this.followers = resp.data.followers;
        console.log(resp.data.followers.length);
        // this.followers_length = resp.data.followers.length;
        this.num2 = resp.data.followers.length;
        this.$store.dispatch("setFollowers", resp.data.followers);
      });
    },
    following() {
      console.log("팔로잉 목록");
      axios({
        url: process.env.VUE_APP_API_URL + "/api/meet/user/following",
        method: "GET",
        headers: {
          Authorization: "Bearer " + this.$store.state.jwt,
        },
      }).then((resp) => {
        console.log("팔로잉 목록 : ", resp);
        this.followings = resp.data.followers;
        this.num1 = resp.data.followers.length;
        this.$store.dispatch("setFollowing", resp.data.following);
      });
    },
  },
};
</script>
<style scoped>
.board-container {
  overflow-y: scroll;
}
.board-container::-webkit-scrollbar {
  display: none;
}
.background-div {
  padding: 0.5rem 5rem 0.5rem 5rem;
  text-align: center;
}
.board-div {
  width: 24rem;
  height: 32rem;
  display: inline-block;
  background-color: #ffe8e8;
  padding: 2rem 2rem;
  border-radius: 1rem;
}
.v-card-title {
  text-align: center;
}
.report-icon {
  position: absolute;
  top: 15px;
  right: 20px;
  cursor: pointer;
}
</style>
