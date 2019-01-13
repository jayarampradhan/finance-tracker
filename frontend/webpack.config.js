const path = require("path");
const CopyWebpackPlugin = require('copy-webpack-plugin');
const webpack = require('webpack');

module.exports ={
    mode: "development",
    entry: [path.resolve(__dirname, "build/classes/kotlin/main/frontend.js"),
            path.resolve(__dirname, "src/asset/scss/app.scss")
            ],
    output: {
        path: path.resolve(__dirname, "build/web"),
        filename: "[name].js",
        libraryTarget: 'umd'
    },
    devtool: "source-map",
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
                test: /\.js$/,
                use: ["source-map-loader"],
                enforce: "pre"
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
                "kotlinx-html-js": path.resolve(__dirname, "build/kotlin-js-min/main/kotlinx-html-js.js"),
            },
            extensions: ['.js', '.jsx', '.scss', '.css'],
            modules: [path.resolve(__dirname, 'src'), 'node_modules']
    },
    plugins: [
            new CopyWebpackPlugin([{ from: path.resolve(__dirname, "src/main/web") },
//                { from: path.resolve(__dirname, "node_modules/kotlinx-html/kotlinx-html-js.js"), to: "js/asset/"}
            ]),
            new webpack.SourceMapDevToolPlugin({
                filename: 'source-map/[file].map',
                publicPath: 'https://cdn.farmiculture.in/financetracker/js',
                fileContext: 'public'
            })
        ]
}