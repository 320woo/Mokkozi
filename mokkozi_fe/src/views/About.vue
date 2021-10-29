<template>
  <div class="about">
    <h1>This is an about page</h1>
    <button @click="moveAddress">Board</button>
  </div>
</template>

<script>
export default ({
  name: 'About',
  component: {
  },
  methods: {
    moveAddress (event) {
      const { innerText } = event.target
      switch (innerText) {
        case 'Board':
          this.$router.push({ path: 'board' })
      }
    }
  }
})
</script>
