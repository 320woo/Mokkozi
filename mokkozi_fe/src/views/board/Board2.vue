<template>
  <v-container>
    <div id="app">
      <v-app id="inspire">
          <v-layout row>
          <v-flex>
              <v-card>
                <template v-for="(beer, index) in beers">
                  <v-layout
                    :key="index"
                  >
                    <v-flex xs5>
                      <v-img
                        :src="beer.img"
                        height="125px"
                        contain
                      ></v-img>
                    </v-flex>
                    <v-flex xs7>
                      <v-card-title primary-title>
                        <div>
                          <div class="headline" v-html="beer.name"></div>
                          <div v-html="beer.tagline"></div>
                        </div>
                      </v-card-title>
                    </v-flex>
                  </v-layout>
                </template>
                <div v-if="beers.length === 0">
                <v-progress-circular
                    indeterminate
                    color="primary"
                    class="bottom"
                  />
                </div>
              </v-card>
          </v-flex>
        </v-layout>
      </v-app>
    </div>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Board2',
  components: {},
  data: () => ({
    bottom: false,
    beers: []
  }),
  methods: {
    bottomVisible () {
      const scrollY = window.scrollY
      const visible = document.documentElement.clientHeight
      const pageHeight = document.documentElement.scrollHeight
      const bottomOfPage = visible + scrollY >= pageHeight
      return bottomOfPage || pageHeight < visible
    },
    addBeer () {
      axios.get('https://api.punkapi.com/v2/beers/random')
        .then(response => {
          const api = response.data[0]
          const apiInfo = {
            name: api.name,
            img: api.image_url,
            tagline: api.tagline
          }
          this.beers.push(apiInfo)
          if (this.bottomVisible()) {
            this.addBeer()
          }
        })
    }
  },
  watch: {
    bottom (bottom) {
      if (bottom) {
        this.addBeer()
      }
    }
  },
  created () {
    window.addEventListener('scroll', () => {
      this.bottom = this.bottomVisible()
    })
    this.addBeer()
  }
}
</script>
