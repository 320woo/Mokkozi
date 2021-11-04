<template>
  <div>
    <br>
      <h1 style="display:flex; justify-content:center">게시판</h1>
    <br>
    <div class="background-div" v-for="image in images" :key="image.id">
      <div class="board-div">
        <v-card
          class="board-card"
          max-width="24rem"
          height="26rem"
        >
          <v-card-title style="display:flex; justify-content:space-between">DaRamG Rider<i class="fas fa-ellipsis-h"></i></v-card-title>
          <template slot="progress">
            <v-progress-linear
              color="deep-purple"
              height="10"
              indeterminate
            ></v-progress-linear>
          </template>

          <v-img
            width="24rem"
            height="auto"
            max-height="15rem"
            position="center"
            :src="image"
            style="margin-bottom: 0.2rem"
          ></v-img>

          <v-card-text class="like-text"><i class="fas fa-heart" style="color:red"></i> like</v-card-text>

          <v-card-text>
            Small plates, salads & sandwiches - an intimate setting with 12 indoor seats plus patio seating.
          </v-card-text>
          <v-card-text style="color: gray">댓글 더 보기..</v-card-text>

          <v-card-actions>
            <v-btn
              color="#fdb4b5"
            >
              Follow
            </v-btn>
          </v-card-actions>
        </v-card>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Board',
  components: {},
  data: () => ({
    images: [
      'https://images.dog.ceo/breeds/bulldog-english/murphy.jpg',
      'https://images.dog.ceo/breeds/spaniel-japanese/n02085782_2690.jpg',
      'https://images.dog.ceo/breeds/stbernard/n02109525_13702.jpg',
      'https://images.dog.ceo/breeds/papillon/n02086910_933.jpg',
      'https://images.dog.ceo/breeds/ovcharka-caucasian/IMG_20190611_152047.jpg',
      'https://images.dog.ceo/breeds/terrier-toy/n02087046_2843.jpg',
      'https://images.dog.ceo/breeds/cockapoo/Scout.jpg',
      'https://cdn.vuetifyjs.com/images/cards/cooking.png',
      '@/assets/images/다람쥐라이더.jpg',
      'https://dog.ceo/api/breeds/image/random'
    ]
  })
}
</script>

<style scoped>
  .background-div {
    padding: 0.5rem 5rem 0.5rem 5rem;
    text-align: center;
  }
  .board-div {
    width: 24rem;
    height: 30rem;
    display: inline-block;
    background-color: #ffe8e8;
    padding: 2rem 2rem;
  }
  .v-card__title {
    align-items: center;
    display: flex;
    font-size: 1rem;
    font-weight: 500;
    letter-spacing: 0.0125em;
    line-height: 2rem;
    word-break: break-all;
    padding: 0;
  }
  .board-card {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-content: center;
    background-color: #ffe8e8;
  }
  .theme--light.v-card {
    background-color: #ffe8e8;
  }
  .v-sheet.v-card:not(.v-sheet--outlined) {
    box-shadow: none;
  }
  .theme--light.v-card > .v-card__text {
    color: black;
  }
  .v-card__text {
    text-align: start;
    font-size: 0.875rem;
    font-weight: 400;
    line-height: 1.375rem;
    letter-spacing: 0.0071rem;
    padding: 0rem;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
  }
  .v-card__actions {
    display: flex;
    align-items: center;
    padding: 0.2rem;
    justify-content: flex-end;
  }
  .like-text {
    font-weight: 500;
  }
</style>
