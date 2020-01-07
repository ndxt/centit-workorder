const path = require('path')
const webpack = require('webpack')
const package = require('../package.json')
const AssetsPlugin = require('assets-webpack-plugin')

console.log(Object.keys(package.dependencies))

module.exports = {
  entry: {
    vendor: [
      'async-validator',
      'vue-codemirror',
      'vue-hoc',
    ]
  },

  output: {
    path: path.join(__dirname, '../static'),
    filename: 'dll.[name].js',
    library: '[name]'
  },

  plugins: [
    new webpack.DllPlugin({
      path: path.join(__dirname, '../', '[name]-manifest.json'),
      name: '[name]'
    })
  ],

  externals: {
    jquery: 'jQuery',
    lodash: '_',
    mustache: 'Mustache',
    vue: 'Vue',
    'vue-router': 'VueRouter',
    iview: 'iview',
    axios: 'axios',
  },
}
