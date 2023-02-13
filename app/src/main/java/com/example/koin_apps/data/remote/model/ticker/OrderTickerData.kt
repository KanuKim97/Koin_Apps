package com.example.koin_apps.data.remote.model.ticker

data class OrderTickerData (
    val closingPrice: String,
    val prevClosingPrice: String,
    val maxPrice: String,
    val minPrice: String,
    val unitsTraded: String
)