package com.example.domain.entity.api.ticker

data class TickerInfoViewEntity(
    val closingPrice: Int,
    val prevClosingPrice: Int,
    val maxPrice: Int,
    val minPrice: Int,
    val tradedUnits24H: Double
)