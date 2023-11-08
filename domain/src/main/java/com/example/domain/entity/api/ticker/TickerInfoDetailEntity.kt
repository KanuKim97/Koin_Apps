package com.example.domain.entity.api.ticker

data class TickerInfoDetailEntity(
    val openPrice: String,
    val closePrice: String,
    val prevClosingPrice: String,
    val minPrice: String,
    val maxPrice: String,
    val tradedUnits: String,
    val tradedUnits24H: String,
    val accTradeValue: String,
    val accTradeValue24H: String,
    val fluctuatePrice: String,
    val fluctuateRate: String,
    val date: String
)