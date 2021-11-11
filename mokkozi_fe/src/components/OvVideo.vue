<template>
  <!-- <div> -->
  <video @play="onPlay()" autoplay :width="videoWidth" :height="videoHeight"/>
  <!-- <canvas id="overlay" /> -->
  <!-- </div> -->
</template>

<script>
import * as faceapi from 'face-api.js'
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
    this.streamManager.addVideoElement(this.$el)
    // this.streamManager.addVideoElement(this.$el).play()
    // this.streamManager.stream.applyFilter("FaceOverlayFilter",{})
    // .then(filter => {
    //   filter.execMethod(
    //     "setOverlayedImage",{
    //             "uri":"https://cdn.pixabay.com/photo/2013/07/12/14/14/derby-148046_960_720.png",
    //             "offsetXPercent":"-0.2F",
    //             "offsetYPercent":"-0.8F",
    //             "widthPercent":"1.3F",
    //             "heightPercent":"1.0F"
    //     });
    // });
  },
  methods: {
    videoWidthSelect () {
      if (this.streamManager.stream.connection.role === 'SUBSCRIBER') {
        this.videoWidth = '30%'
        this.videoHeight = '30%'
      }
    },
    onPlay (event) {
      if (this.streamManager.paused || this.streamManager.ended)
      { return setTimeout(() => onPlay()) }
      const minConfidence = 0.3
      const maxResult = 100
      const options = new faceapi.SsdMobilenetv1Options({ minConfidence, maxResult })

      // const result = faceapi.detectSingleFace(videoEl, options)

      const displaySize = {width:this.streamManager.width,height:this.streamManager.height}

      // console.log(result)

      // if (result) {
      //   const canvas = document.getElementById('overlay')
      //   const dims = faceapi.matchDimensions(canvas, displaySize)
      //   faceapi.draw.drawDetections(canvas, faceapi.resizeResults(result, dims))
      // }
      // setTimeout(() => onPlay())
      // const canvas = document.getElementById('overlay')
      // console.log(canvas);
      // console.log(this.streamManager)
      // faceapi.matchDimensions(canvas, displaySize,true);
      // setInterval(async ()=>{
      //   const detections = await faceapi
      //   .detectAllFaces(this.streamManager,options)
      //   .withFaceLandmarks();
      //   const resizedDetections = faceapi.resizeResults(detections,displaySize);
      //   canvas.getContext("2d").clearRect(0,0,canvas.width,canvas.height);
      //   faceapi.draw.drawDetections(canvas,resizedDetections);
      //   faceapi.draw.drawFaceLandmarks(canvas, resizedDetections);
      // },100);
    }
  },
  created () {
    // 모델 로드
    Promise.all([
      faceapi.nets.ssdMobilenetv1.loadFromUri('/weights'),
      faceapi.nets.faceLandmark68Net.loadFromUri('/weights'),
      faceapi.nets.faceRecognitionNet.loadFromUri('/weights')
    ]).then()
  }
})
</script>
