<template>
  <!-- 컴포넌트를 2개 넣어야 한다. -->
  <v-container class="root-component">
    <v-row class="com" >
      <v-col>
        <div class="pa-5 d-flex justify-center flex-wrap flex-column side-page">
          <!-- 로고 이미지 -->
          <div class="mb-10">
            <v-img src="@/assets/logo.png" max-height="200" max-width="200" alt="로고 이미지"></v-img>
          </div>
          <!-- 메인 이미지 -->
          <div class="mb-10">
            <v-img class="main-img" src="@/assets/images/main.png"  alt="로고 이미지">
            </v-img>
          </div>
          <!-- 서비스 소개 문구 -->
          <div>
            <h3>새로운 AR 소개팅 서비스를 체험해 보세요</h3>
            <br>
            <p>쉽고 빠르게, 그리고 부담없이 마음에 드는 이성을 찾고 싶다면?<br>
            지금 바로 모꼬지를 통해 새로운 만남을 경험해 보세요
            </p>
          </div>
        </div>
      </v-col>

      <v-col>
        <div class="pa-5 d-flex justify-center flex-wrap flex-column side-page">
          오른쪽 페이지

        </div>

      </v-col>
    </v-row>
  </v-container>

</template>

<script>

export default {
  name: 'Home',

  components: {
  }
}
</script>

<style lang="scss" scoped>
.root-component {
  width: 1300px;
}
.com {
  height: 937px;
  background-color: white;
}
.side-page * {
  margin: auto;
}
.main-img {
  border-radius: 30px;
}
</style>
