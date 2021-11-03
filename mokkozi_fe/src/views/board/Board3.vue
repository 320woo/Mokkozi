<template>
    <div class="followlist">
      <followListImg v-for="(item,index) in following" :key="index" v-bind:name="item.name" v-bind:no="item.no" v-bind:followState="item.followState"/>
      <infinite-loading @infinite="infiniteHandler" spinner="spiral">
        <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;">더 이상 목록이 없습니다.</div>
      </infinite-loading>
    </div>
</template>

<script>
// import axios from 'axios'

export default ({
  name: 'board3',
  components: {},
  data: () => ({
    page: 0
  })
  // methods: {
  //   infiniteHandler ($state) {
  //     axios({
  //       method: 'get',
  //       url: `${SERVER_URL}/profile/${userpk}/memfollowings` + "?page=" + (this.page),
  //       headers: this.getToken
  //     }).then(res => {
  //       setTimeout(() => { // 로딩스피너를 위해 1초의 지연시간을 설정했다.
  //         if (res.data.length) {
  //           this.following = this.following.concat(res.data)
  //           $state.loaded()
  //           this.page += 1
  //           // 끝 지정(No more data) - 데이터가 n개 미만이면
  //           if (res.data.length / 20 < 1) { // 종료조건
  //             $state.complete()
  //           }
  //         } else {
  //           // 끝 지정(No more data)
  //           $state.complete()
  //         }
  //       }, 1000)
  //     }).catch(err => {
  //       console.error(err)
  //     })
  //   }
  // }
})
</script>
