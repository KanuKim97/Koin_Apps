package com.example.domain.entity.api.ticker

data class TickerOrderDataEntity(
    var closingPrice: Int,
    var prevClosingPrice: Int,
    var maxPrice: Int,
    var minPrice: Int,
    var unitsTraded_24H: Double
)