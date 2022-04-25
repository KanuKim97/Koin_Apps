package com.example.koin_apps.data.remote.model.ticker

data class TickerList(
    var openTickerPrice: Int,
    var closeTickerPrice: Int,
    var minTickerPrice: Int,
    var maxTickerPrice: Int,
    var tickerTradedUnits: Double,
    var tickerTradeValue: Double,
    var prevTickerClosePrice: Int,
    var tickerTraded_Units_24H: Double,
    var tickerTraded_Value_24H: Double,
    var tickerFluctated_24H: Int,
    var tickerFluctated_Rate_24H: Double,
    var tickerDate: Double
    )
