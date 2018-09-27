const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');

module.exports = {
  mode: 'development',
	context: __dirname,
	entry: {
		app: './src/main.ts'
	},
	output: {
		path: __dirname + '/js',
		filename: 'bundle.js'
	},
	plugins: [
		// new webpack.optimize.CommonsChunkPlugin(/* chunkName= */"vendor",  /*fileName=*/ "app.bundle.js"),
    new HtmlWebpackPlugin({
      template: './src/index.html',
      filename: 'index.html',
      inject: 'head'
    }),
    new webpack.DefinePlugin({
      // global app config object
      config: JSON.stringify({
        apiUrl: 'http://localhost:4000'
      })
    }),
    new webpack.ContextReplacementPlugin(
        /angular(\\|\/)core(\\|\/)/, path.resolve(__dirname, '../src')
    )
	],
	module: {
		rules: [
      { test: /\.ts$/, use: ['ts-loader', 'angular2-template-loader'], exclude: /(node_modules)/ },
      { test: /\.(html|css)$/, loader: 'raw-loader' }
    ]
	},
  resolve: {
    extensions: ['.ts', '.js']
  },
  optimization: {
    splitChunks: {
      chunks: 'all',
    },
    runtimeChunk: true
  },
  devServer: {
    historyApiFallback: true
  }
};