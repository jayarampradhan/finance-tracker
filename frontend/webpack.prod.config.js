const path = require("path");
const CopyWebpackPlugin = require('copy-webpack-plugin');
const webpack = require('webpack');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const OptimizeCSSAssetsPlugin = require("optimize-css-assets-webpack-plugin");

module.exports ={
    mode: "production",
    entry: [path.resolve(__dirname, "build/classes/kotlin/main/frontend.js"),
            path.resolve(__dirname, "src/asset/scss/app.scss")

    ],
    output: {
        path: path.resolve(__dirname, "build/web"),
        "filename": "[name].js",
        libraryTarget: 'umd'
    },
    optimization: {
        minimizer: [new UglifyJsPlugin({
            extractComments: true,
            parallel: true,
        }),
        new OptimizeCSSAssetsPlugin({})
        ]
    },
    module: {
        rules: [
            {
                enforce: "pre",
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: "eslint-loader"
            },
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader",
                    options: {
                        presets: [
                            "env"
                        ]
                    }
                }
            },
            {
                test:/\.(sa|sc)ss$/,
                use:[
                    {
                        loader: 'file-loader',
                        options: {
                            name: 'css/[name].blocks.css',
                        }
                    },
                    {
                        loader: 'extract-loader'
                    },
                    {
                        loader: "css-loader?-url",
                            options: {
                                sourceMap: true
                            }
                    }, {
                        loader: "sass-loader",
                            options: {
                                sourceMap: true
                            }
                    }]
            }
        ]
    },
    resolve: {
            alias: {
                kotlin: path.resolve(__dirname, "build/kotlin-js-min/main/kotlin.js"),
                bootstrapStyle: path.resolve(__dirname, "node_modules/bootstrap/scss/"),
                fontAwesomeStyle: path.resolve(__dirname, "node_modules/@fortawesome/fontawesome-free/scss/"),
                commonStyle: path.resolve(__dirname, "src/asser/scss/common/"),
                "kotlinx-html-js": path.resolve(__dirname, "build/kotlin-js-min/main/kotlinx-html-js.js"),
            },
            extensions: ['.js', '.jsx', '.scss', '.css'],
            modules: [path.resolve(__dirname, 'src'), 'node_modules']
    },
    plugins: [
            new CopyWebpackPlugin([{ from: path.resolve(__dirname, "src/main/web") }]),
        ]
}