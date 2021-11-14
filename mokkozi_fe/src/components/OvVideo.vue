<template>
  <div id="canvas">
    <video id="user_video" autoplay :width="videoWidth" :height="videoHeight"/>
    <!-- <video id="user_video" autoplay :width="videoWidth" :height="videoHeight"/> -->
  </div>
</template>

<script>
import * as faceapi from 'face-api.js'
import { resizeResults } from 'face-api.js'

export default ({
  name: 'OvVideo',
  data: () => ({
    videoWidth: '100%',
    videoHeight: '100%',
    canvasWidth: '',
    canvasHeight: ''
  }),
  props: {
    streamManager: Object
  },
 watch: {
    streamManager () {
      this.streamManager.addVideoElement(document.querySelector("#user_video"))
      // this.streamManager.addVideoElement(this.$el)
    }
  },
  mounted () {
    this.videoWidthSelect()
    // const video = document.querySelector("#user_video");
    // Promise.all([
    //   faceapi.nets.ssdMobilenetv1.loadFromUri('/weights'),
    //   faceapi.nets.faceLandmark68Net.loadFromUri('/weights'),
    //   faceapi.nets.faceRecognitionNet.loadFromUri('/weights')
    // ]).then(()=>{
    //       this.streamManager.addVideoElement(video);
    //       // this.addVideoStream (video);
    // }

    // )
    const video = document.getElementById('user_video')

    Promise.all([
      faceapi.nets.tinyFaceDetector.loadFromUri('/weights'),
      faceapi.nets.ssdMobilenetv1.loadFromUri('/weights'),
      faceapi.nets.faceLandmark68Net.loadFromUri('/weights'),
      faceapi.nets.faceRecognitionNet.loadFromUri('/weights'),
      // faceapi.nets.faceExpressionNet.loadFromUri('/weights') // 이 부분을 불러오지 못해서 안됐던 것!!
    ]).then(() => {
        this.streamManager.addVideoElement(video)
      })

    video.addEventListener('play', () => {
      const canvas = faceapi.createCanvasFromMedia(video)
      document.getElementById('canvas').append(canvas)
      const displaySize = { width: 600, height: 400 }
      console.log('디스플레이 사이즈', displaySize)
      faceapi.matchDimensions(canvas, displaySize)
      setInterval(async () => {
        const detections = await faceapi.detectAllFaces(video, new faceapi.TinyFaceDetectorOptions())
        // .withFaceExpressions() -> 위에서 불러오지 못한다
        // console.log(detections)
        const resizedDetections = faceapi.resizeResults(detections, displaySize)
        canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height)
        faceapi.draw.drawDetections(canvas, resizedDetections)
        // faceapi.draw.DrawFaceLandmarks(canvas, resizedDetections)
      }, 100)
    })

  },
  methods: {
    videoWidthSelect () {
      if (this.streamManager.stream.connection.role === 'SUBSCRIBER') {
        this.videoWidth = '30%'
        this.videoHeight = '30%'
      }
    },

    startVideo () {
      const video = document.getElementById('user_video') // 비디오 가져와서
      navigator.getUserMedia( // 필요한 요소 3개 err도 넣어줘야한다!
        {video: {} },
        stream => video.srcObject = stream,
        err => console.error(err)
      )
    }


    // addVideoStream () {

      // const video = document.querySelector("#user_video");
      // const testVideo = document.querySelector('.test_video')
      // testVideo.append(video)
      // // this.streamManager.addVideoElement(video);
      // // video.enabled = true;
      //   if(video.paused || video.ended)
      //   {return setTimeout(() => onPlay())}

      //   if (document.querySelector("canvas")) {
      //   document.querySelector("canvas").remove()};

    //     const minConfidence = 0.3
    //     const maxResult = 100
    //     const options = new faceapi.SsdMobilenetv1Options({minConfidence,maxResult})

    //     const canvas = faceapi.createCanvasFromMedia(video);
    //     testVideo.append(canvas);

    //     const displaySize = { width:video.width,height:video.height };
    //     faceapi.matchDimensions(canvas, displaySize);

    //     setInterval(async ()=>{
    //     const detections = await faceapi
    //     .detectAllFaces(video,options)
    //     .withFaceLandmarks();

    //     const resizedDetections = faceapi.resizeResults(detections,displaySize);
    //     canvas.getContext("2d").clearRect(0,0,canvas.width,canvas.height);
    //     faceapi.draw.drawDetections(canvas,resizedDetections);
    //     faceapi.draw.drawFaceLandmarks(canvas, resizedDetections);

    //   },100);

    // }
  },
})
</script>

<style scoped>
  /* #canvas {
    position: absolute;
  } */
  /* #user_video {
    position: absolute;
  } */
</style>
