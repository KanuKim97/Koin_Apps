package com.example.koin_apps.data.remote.model.ticker

data class TickerRoot(
    var status: String,
    var data: Map<String?, TickerData?>,
    var message: String
)