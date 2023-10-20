package com.example.data.remote.model.ticker

import com.google.gson.annotations.SerializedName

data class TickerAllRoot(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val tickerData: Map<String, TickerData>?
)