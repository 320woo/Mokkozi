<template>
  <v-app>
    <v-main style="background-color: #FFE8E8;">
      <v-container class="com">
        <v-row no-gutters>
          <!-- 소개 페이지 -->
          <v-col cols="5" class="d-none d-md-flex logo-page"> <!-- 창 크기에 따라 전시 or 비전시 -->
            <div class="pa-5 d-flex flex-wrap flex-column">
              <!-- 로고 이미지 -->
              <div class="mb-10">
                <v-img src="@/assets/logo.png" max-height="200" max-width="200" alt="로고 이미지"></v-img>
              </div>
              <!-- 메인 이미지 -->
              <div class="mb-10">
                <v-img class="main-img" src="@/assets/images/main.png"  alt="메인 이미지">
                </v-img>
              </div>
              <!-- 서비스 소개 문구 -->
              <div style="text-align: center;">
                <h3>새로운 AR 소개팅 서비스를 체험해 보세요</h3>
                <br>
                <p>쉽고 빠르게, 그리고 부담없이 마음에 드는 이성을 찾고 싶다면?<br>
                지금 바로 모꼬지를 통해 새로운 만남을 경험해 보세요
                </p>
              </div>
            </div>
          </v-col>
          <!-- 매번 바뀌는 페이지 -->
          <v-col class="d-flex flex-row justify-center align-center" cols="7">
          <top></top>
            <main>
              <router-view/>
            </main>
            <foot></foot>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default ({
  components: {},
  name: 'App',
  data: () => ({

  })
})
</script>

<style lang="scss" scoped>
.com {
  height: 100%;
  background-color: white;
  position: relative;
}
.main-img {
  border-radius: 30px;
}
.logo-page * {
  margin : auto;
}
</style>
