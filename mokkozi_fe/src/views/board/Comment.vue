<template>
  <div class="background-div">
    <div class="comment-div">
      <v-card
        class="board-card"
        max-width="24rem"
        height="auto"
      >
        <!-- <v-card-title style="display:flex; justify-content:space-between;" class="font-weight-bold"><v-icon @click="BackToBoardClick">fas fa-chevron-left</v-icon>댓글<v-icon>fas fa-ellipsis-h</v-icon></v-card-title> -->
        <v-toolbar
            color="#ffe8e8"
            style="box-shadow: none;"
          >
            <v-btn icon>
              <v-icon @click="BackToBoardClick">fas fa-chevron-left</v-icon>
            </v-btn>
            <v-spacer></v-spacer>
            <v-toolbar-title class="font-weight-bold">댓글</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-list-item-avatar @click="UserImageClick">
              <img src="https://picsum.photos/250/300?image=1008">
            </v-list-item-avatar>
          </v-toolbar>
        <v-divider style="margin: 0.5rem 0rem;"></v-divider>
        <div style="display: flex; flex-direction: row; justify-content: center">
          <div style="width: 3rem;">
            <v-list-item-avatar>
              <img src="https://picsum.photos/250/300?image=821">
            </v-list-item-avatar>
          </div>
          <div style="width: 17rem;">
            <v-list-item-content>
              <span class="font-weight-bold" style="text-align: start;">Spike Lee</span>
              <span class="comment-text">I'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhood</span>
            </v-list-item-content>
          </div>
        </div>
        <div style="display: flex; flex-direction: row; justify-content: center">
          <div style="width: 3rem;">
            <v-list-item-avatar>
              <img src="https://picsum.photos/250/300?image=821">
            </v-list-item-avatar>
          </div>
          <div style="width: 17rem;">
            <v-list-item-content>
              <span class="font-weight-bold" style="text-align: start;">Spike Lee</span>
              <span class="comment-text">I'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhood</span>
            </v-list-item-content>
          </div>
        </div>
        <div style="display: flex; flex-direction: row; justify-content: center">
          <div style="width: 3rem;">
            <v-list-item-avatar>
              <img src="https://picsum.photos/250/300?image=821">
            </v-list-item-avatar>
          </div>
          <div style="width: 17rem;">
            <v-list-item-content>
              <span class="font-weight-bold" style="text-align: start;">Spike Lee</span>
              <span class="comment-text">I'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhood</span>
            </v-list-item-content>
          </div>
        </div>
        <div style="display: flex; flex-direction: row; justify-content: center">
          <div style="width: 3rem;">
            <v-list-item-avatar>
              <img src="https://picsum.photos/250/300?image=821">
            </v-list-item-avatar>
          </div>
          <div style="width: 17rem;">
            <v-list-item-content>
              <span class="font-weight-bold" style="text-align: start;">Spike Lee</span>
              <span class="comment-text">I'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhood</span>
            </v-list-item-content>
          </div>
        </div>
        <div style="display: flex; flex-direction: row; justify-content: center">
          <div style="width: 3rem;">
            <v-list-item-avatar>
              <img src="https://picsum.photos/250/300?image=821">
            </v-list-item-avatar>
          </div>
          <div style="width: 17rem;">
            <v-list-item-content>
              <span class="font-weight-bold" style="text-align: start;">Spike Lee</span>
              <span class="comment-text">I'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhood</span>
            </v-list-item-content>
          </div>
        </div>
        <div style="display: flex; flex-direction: row; justify-content: center">
          <div style="width: 3rem;">
            <v-list-item-avatar>
              <img src="https://picsum.photos/250/300?image=821">
            </v-list-item-avatar>
          </div>
          <div style="width: 17rem;">
            <v-list-item-content>
              <span class="font-weight-bold" style="text-align: start;">Spike Lee</span>
              <span class="comment-text">I'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhood</span>
            </v-list-item-content>
          </div>
        </div>
        <div style="display: flex; flex-direction: row; justify-content: center">
          <div style="width: 3rem;">
            <v-list-item-avatar>
              <img src="https://picsum.photos/250/300?image=821">
            </v-list-item-avatar>
          </div>
          <div style="width: 17rem;">
            <v-list-item-content>
              <span class="font-weight-bold" style="text-align: start;">Spike Lee</span>
              <span class="comment-text">I'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhood</span>
            </v-list-item-content>
          </div>
        </div>
        <div style="display: flex; flex-direction: row; justify-content: center">
          <div style="width: 3rem;">
            <v-list-item-avatar>
              <img src="https://picsum.photos/250/300?image=821">
            </v-list-item-avatar>
          </div>
          <div style="width: 17rem;">
            <v-list-item-content>
              <span class="font-weight-bold" style="text-align: start;">Spike Lee</span>
              <span class="comment-text">I'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhoodI'll be in your neighborhood</span>
            </v-list-item-content>
          </div>
        </div>
        <div>
            <input style="height: 1.25rem; font-size: 0.875rem; border: none; width: 16rem" type="text" placeholder="댓글 달기">
            <v-btn
              color="#fdb4b5"
              width="4rem"
              height="1.25rem"
            >
              작성
            </v-btn>
          </div>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Comment',
  components: {},
  data: () => ({
  }),
  methods: {
    BackToBoardClick () {
      this.$router.push({ name: 'Board' })
    },
    UserImageClick () {
      this.$router.push({ name: 'Profile' })
    }
  }
}
</script>

<style scoped>
  .background-div {
      padding: 0.5rem 5rem 0.5rem 5rem;
      text-align: center;
    }
  .comment-div {
    width: 24rem;
    height: 50rem;
    display: inline-block;
    background-color: #ffe8e8;
    padding: 2rem 2rem;
    border-radius: 2rem;
    overflow-y: scroll;
  }
  .comment-div::-webkit-scrollbar {
    display: none;
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
    border-radius: 2rem;
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
  .v-list-item {
    align-items: center;
    display: flex;
    flex: 1 1 100%;
    letter-spacing: normal;
    min-height: 2rem;
    max-width: "24rem";
    padding: 0;
    outline: none;
    position: relative;
    text-decoration: none;
  }
  .v-list-item__content {
    align-items: center;
    align-self: center;
    display: flex;
    flex-wrap: wrap;
    flex: 0;
    overflow: hidden;
    padding: 12px;
  }
  .comment-text {
    word-break:break-all;
    text-align: start;
  }
</style>
