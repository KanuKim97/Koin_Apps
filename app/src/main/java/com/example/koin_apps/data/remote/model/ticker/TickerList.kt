package com.example.koin_apps.data.remote.model.ticker

import com.google.gson.annotations.SerializedName

data class TickerList(
    @SerializedName("openTickerPrice")
    var openTickerPrice: Int,

    @SerializedName("closeTickerPrice")
    var closeTickerPrice: Int,

    @SerializedName("minTickerPrice")
    var minTickerPrice: Int,

    @SerializedName("maxTickerPrice")
    var maxTickerPrice: Int,

    @SerializedName("tickerTradedUnits")
    var tickerTradedUnits: Double,

    @SerializedName("tickerTradeValue")
    var tickerTradeValue: Double,

    @SerializedName("prevTickerClosePrice")
    var prevTickerClosePrice: Int,

    @SerializedName("tickerTraded_Units")
    var tickerTraded_Units_24H: Double,

    @SerializedName("tickerTraded_Value_24H")
    var tickerTraded_Value_24H: Double,

    @SerializedName("tickerFlucatated_24H")
    var tickerFluctated_24H: Int,

    @SerializedName("tickerFlucatated_Rate_24H")
    var tickerFluctated_Rate_24H: Double,

    @SerializedName("tickerDate")
    var tickerDate: Double
    )
