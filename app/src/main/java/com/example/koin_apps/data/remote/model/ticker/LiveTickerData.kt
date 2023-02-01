package com.example.koin_apps.data.remote.model.ticker

data class LiveTickerData (
    val closing_price: String,
    val fluctate_24H: String,
    val fluctate_rate_24H: String
)