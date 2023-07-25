package com.example.domain.entity.api.ticker

data class TickerLiveDataEntity(
    var closingPrice: Int,
    var fluctate_24H: Int,
    var fluctate_Rate: Double
)
