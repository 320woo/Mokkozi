<template>
  <!-- <div class="test_video"> -->
  <video autoplay :width="videoWidth" :height="videoHeight"/>
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
    // const video = document.createElement("video");

    // navigator.mediaDevices.getUserMedia({
    //   video:true,
    //   audio:true,
    // }).then((stream)=>{
    //   this.streamManager.addVideoElement(this.video)
    //   this.addVideoStream(stream);

    // })


    // streamManager.addEventListener("play",()=>{
    //   if(document.querySelector("canvas")){
    //     document.querySelector("canvas").remove();
    //   }
    //   const canvas = faceapi.createCanvasFromMedia(this.streamManager.stream);
    //   // videoGrid.append(canvas);



    // })
  },
  methods: {
    videoWidthSelect () {
      if (this.streamManager.stream.connection.role === 'SUBSCRIBER') {
        this.videoWidth = '30%'
        this.videoHeight = '30%'
      }
    },
    addVideoStream (video, stream) {
      video.srcObject = stream;
      video.addEventListener("loadedmetadata",()=>{
        // video.play();
      });



      const testVideo = document.querySelector('.test_video')
      testVideo.append(this.video)
      this.streamManager.addVideoElement(this.video);
      this.video.enabled = true;
      video.addEventListener("play",()=>{
        if (document.querySelector("canvas")) {
        document.querySelector("canvas").remove();
        }

        const canvas = faceapi.createCanvasFromMedia(this.video);


      })

      // if (this.streamManager.paused || this.streamManager.ended)
      // { return setTimeout(() => onPlay()) }
      // const minConfidence = 0.3
      // const maxResult = 100
      // const options = new faceapi.SsdMobilenetv1Options({ minConfidence, maxResult })

      // // const result = faceapi.detectSingleFace(videoEl, options)

      // const displaySize = {width:this.streamManager.width,height:this.streamManager.height}

      // // console.log(result)

      // // if (result) {
      // //   const canvas = document.getElementById('overlay')
      // //   const dims = faceapi.matchDimensions(canvas, displaySize)
      // //   faceapi.draw.drawDetections(canvas, faceapi.resizeResults(result, dims))
      // // }
      // // setTimeout(() => onPlay())
      // // const canvas = document.getElementById('overlay')
      // // console.log(canvas);
      // // console.log(this.streamManager)
      // // faceapi.matchDimensions(canvas, displaySize,true);
      // // setInterval(async ()=>{
      // //   const detections = await faceapi
      // //   .detectAllFaces(this.streamManager,options)
      // //   .withFaceLandmarks();
      // //   const resizedDetections = faceapi.resizeResults(detections,displaySize);
      // //   canvas.getContext("2d").clearRect(0,0,canvas.width,canvas.height);
      // //   faceapi.draw.drawDetections(canvas,resizedDetections);
      // //   faceapi.draw.drawFaceLandmarks(canvas, resizedDetections);
      // // },100);
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
