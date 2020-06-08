config.devServer = Object.assign(config.devServer || {}, {
    watchOptions: {
        "aggregateTimeout": 5000,
        "poll": 1000
    }
})
