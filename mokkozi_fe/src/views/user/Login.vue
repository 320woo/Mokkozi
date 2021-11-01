<template>
  <v-container fluid>
    <v-row>
      <v-col>
        하이하이
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'Login',
  props: {},
  data: () => ({
    user: {
      email: '',
      password: ''
    }
  })
}
</script>
