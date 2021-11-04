module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    https: true,
    port: 3000,
    open: true,
    proxy: {
      '/api/v1': {
        target: 'https://localhost:8443/'
      },
      '/webjars': {
        target: 'https://localhost:8443/'
      },
      '/group-call': {
        target: 'https://localhost:8443/'
      },
      '/upload': {
        target: 'https://localhost:8443/'
      }
    },
    historyApiFallback: true,
    hot: true
  },
  // css: {
  //   requireModuleExtension: false // import 시에 경로에 .module 포함 안해도 된다.
  // },
  lintOnSave: false,
  outputDir: './dist'
}
