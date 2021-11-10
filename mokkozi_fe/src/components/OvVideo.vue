<template>
  <video autoplay :width="videoWidth" :height="videoHeight"/>
</template>

<script>
export default ({
  name: 'OvVideo',
  data: () => ({
    videoWidth: '100%',
    videoHeight: '100%'
  }),
  props: {
    streamManager: Object
  },

  watch: {
    streamManager () {
      this.streamManager.addVideoElement(this.$el)
    }
  },
  mounted () {
    this.videoWidthSelect()
    console.log('영상 출력하기 위해 받은 값 streamManager', this.streamManager)
    console.log('영상 출력하기 위해 받은 값 streamManager', this.streamManager.stream)
    console.log('역할이 무엇인지 !!!!', this.streamManager.stream.connection.role)
    console.log('비디오 출력을 위해 Element에 추가해주는 값: ', this.streamManager.addVideoElement(this.$el))
    this.streamManager.addVideoElement(this.$el)
  },
  methods: {
    videoWidthSelect () {
      if (this.streamManager.stream.connection.role === 'SUBSCRIBER') {
        this.videoWidth = '30%'
        this.videoHeight = '30%'
      }
    }
  }
})
</script>
