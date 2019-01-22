const path = require("path");
const CopyWebpackPlugin = require('copy-webpack-plugin');
const webpack = require('webpack');
//import * as path from 'path';
//import * as CopyWebpackPlugin from 'copy-webpack-plugin';
//import * as webpack from 'webpack';

module.exports ={
    mode: "development",
    entry: [path.resolve(__dirname, "build/classes/kotlin/main/frontend.js"),
            path.resolve(__dirname, "src/asset/scss/app.scss"),
            ],
    output: {
        path: path.resolve(__dirname, "build/web"),
        filename: "[name].js",
        libraryTarget: 'umd'
    },
    devtool: "source-map",
    module: {
        rules: [
            {test: /\.(woff|woff2|eot|ttf|otf|svg)$/,
                loader: "file-loader",
                options: {
                     name: '[name].[ext]',
                     outputPath: 'font/'
                }
            },
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
//                kotlin: path.resolve(__dirname, "build/kotlin-js-min/main/kotlin.js"),
                kotlin: path.resolve(__dirname, "node_modules/kotlin/kotlin.js"),
                bootstrapStyle: path.resolve(__dirname, "node_modules/bootstrap/scss/"),
                fontAwesomeStyle: path.resolve(__dirname, "node_modules/@fortawesome/fontawesome-free/"),
                commonStyle: path.resolve(__dirname, "src/asset/scss/common/"),
//                "kotlinx-html-js": path.resolve(__dirname, "build/kotlin-js-min/main/kotlinx-html-js.js"),
                "kotlinx-html-js": path.resolve(__dirname, "node_modules/kotlinx-html/kotlinx-html-js.js"),
                'jquery': path.resolve(__dirname, "node_modules/jquery/dist/jquery.slim.js"),
            }
    },
    plugins: [
            new CopyWebpackPlugin([{ from: path.resolve(__dirname, "src/main/web") }]),
            new webpack.SourceMapDevToolPlugin({
                filename: 'source-map/[file].map',
                publicPath: 'https://cdn.farmiculture.in/financetracker/js',
                fileContext: 'public'
            }),
            new webpack.ProvidePlugin({
                        $: 'jquery',
                        jQuery: 'jquery',
                        'window.jQuery': 'jquery',
                        Tether: 'tether',
                        Popper: ['popper.js', 'default']
            })
        ]
}