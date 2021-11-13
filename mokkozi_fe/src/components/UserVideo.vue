<template>
  <div v-if="streamManager">
    <ov-video :stream-manager="streamManager"/>
    <div><p>{{ clientData }}</p></div>
  </div>
</template>

<script>
import OvVideo from './OvVideo'
// import * as faceapi from 'face-api.js'
export default {
  name: 'UserVideo',

  components: {
    OvVideo
  },

  props: {
    streamManager: Object
  },
  computed: {
    clientData () {
      const { clientData } = this.getConnectionData()
      return clientData
    },
    // playCanvas(){
    //   const canvas = document.getElementById('overlay')
    //   canvas = this.onPlay()
    //   return canvas
    // }
  },
  methods:{
    getConnectionData () {
      const { connection } = this.streamManager.stream
      return JSON.parse(connection.data)
    },
  }
}
</script>
