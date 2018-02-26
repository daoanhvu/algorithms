var path = require('path');

module.exports = {
	entry: './jsx/index.jsx',
	output: {
		path: __dirname,
		filename: '../bkda/src/main/resources/static/bundle.js'
	},
	module: {
		loaders: [
			{
				test: /\.jsx$/,
				exclude: /(node_modules)/,
//				loaders: ['babel-loader?presets[]=react,presets[]=es2015']
				loader: 'babel-loader',
				query: {
					presets: ['stage-0', 'es2015', 'react']
				}
			},
			{
				test: /\.css$/,
				loader: 'style-loader!css-loader'
			}
		]
	}
};