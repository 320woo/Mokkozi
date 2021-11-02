<template>
  <div>
    <div class="profile" style="height: 100%;">
    <!-- 배경 이미지 부분 -->
    <img class="bg-img" :src="defaultImage" alt="커버사진" style="height:250px;">
        <!-- 사용자 프로필 이미지 부분 -->
        <div class="user-img-frame" style="overflow: hidden;">
            <div class="sub-frame">
                <img
                    class="user-img"
                    :src="propImage"
                    alt=""
                    width="100px"
                    height="100px"
                    style="border-radius: 100px; object-fit:cover;"></div>
            </div>
            <!-- 사용자 프로필 이미지 수정 버튼 -->
            <div class="user-img-mod-frame">
                <div class="mod-sub-frame">
                    <label for="ex_file">
                        <img class="bg-img" :src="camera" alt="카메라 아이콘">
                    </label>
                    <input type="file" id="ex_file" @change="propUpdate"/>
                </div>
            </div>
            <div class="user-follow"><v-btn
                dark
                dense
                color="#FFB4B4"
                class="my-2 font-weight">
                팔로우
            </v-btn></div>
            <!-- 인적 사항 부분 -->
    <div>
    <v-card class="mt-10 ml-10 mr-10" elevation="2">
    <v-container>
        <v-layout row="row" wrap="wrap" style="text-align:center; color:#FFB4B4;">
            <v-flex md6="md6" xs12="xs12">
                <div class="font-weight-black">팔로워</div>
                <div class="font-weight-medium">50</div>
            </v-flex>
            <v-flex md6="md6" xs12="xs12">
                <div class="font-weight-black">팔로잉</div>
                <div class="font-weight-medium">30</div>
            </v-flex>
        </v-layout>
    </v-container>
    </v-card>
    <span style="text-align:left">
    <div class="mt-10 ml-10 mr-10">
        <div>저는 이런 사람입니다</div>
        <div>무교, 비흡연자, 술을 못마심, 내성적</div>
        <div>저는 이런 사람을 만나고 싶어요</div>
        <div>무교, 술을 잘마심, 친절함</div>
    </div>
    </span>
          </div>
        </div> <!-- end of 사용자 프로필 -->
  </div>
</template>

<script>
import defaultImage from '../assets/images/커버.png'
import defaultUserImage from '../assets/images/user.png'
import camera from '../assets/images/camera.png'

export default {
  name: 'File',
  components: {},
  data: () => ({
    defaultImage: defaultImage,
    propImage: defaultUserImage,
    camera: camera
  })
}
</script>

<style>
.background-div {
    padding: 0.5rem 5rem 0.5rem 5rem;
    text-align: center;
  }
  .board-div {
    width: 24rem;
    display: inline-block;
  }
</style>
