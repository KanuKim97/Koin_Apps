package com.example.domain.entity.api.ticker

data class TickerDataEntity(
    var openPrice: String,
    var closePrice: String,
    var minPrice: String,
    var maxPrice: String,
    var tradedUnits: String,
    var accTradeValue: String,
    var prevClosingPrice: String,
    var tradedUnits_24H: String,
    var accTradeValue_24H: String,
    var fluctuatePrice: String,
    var fluctuateRate: String,
    var date: Long
)