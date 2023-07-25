package com.example.data.remote.model.ticker

import com.google.gson.annotations.SerializedName

data class TickerData(
    @SerializedName("opening_price") val openPrice: String,
    @SerializedName("closing_price") val closePrice: String,
    @SerializedName("min_price") val minPrice: String,
    @SerializedName("max_price") val maxPrice: String,
    @SerializedName("units_traded") val tradedUnits: String,
    @SerializedName("acc_trade_value") val accTradeValue: String,
    @SerializedName("prev_closing_price") val prevClosingPrice: String,
    @SerializedName("units_traded_24H") val tradedUnits_24H: String,
    @SerializedName("acc_traded_value_24H") val accTradeValue_24H: String,
    @SerializedName("fluctate_24H") val fluctuatePrice: String,
    @SerializedName("fluctate_rate_24H") val fluctuateRate: String,
    @SerializedName("date") val date: Long
)