
module.exports = {
    devServer: {
    // to use http-proxy-middleware (CORS)
      proxy: {
        '/api': {
          target: 'http://localhost:8081', // backends' application.properties server.port
          ws: true,
          changeOrigin: true
        }
      }
    },
    // Change build paths to make them Maven compatible
    outputDir: 'target/dist',
    assetsDir: 'static',
  };