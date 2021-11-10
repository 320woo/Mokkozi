<template>
  <video autoplay />
</template>

<script>
export default ({
  name: 'OvVideo',

  props: {
    streamManager: Object
  },

  watch: {
    streamManager () {
      this.streamManager.addVideoElement(this.$el)
    }
  },
  mounted () {
    console.log('영상 출력하기 위해 받은 값 streamManager', this.streamManager)
    console.log('영상 출력하기 위해 받은 값 streamManager', this.streamManager.stream)
    console.log('비디오 출력을 위해 Element에 추가해주는 값: ', this.streamManager.addVideoElement(this.$el))
    this.streamManager.addVideoElement(this.$el)
  }
})
</script>
