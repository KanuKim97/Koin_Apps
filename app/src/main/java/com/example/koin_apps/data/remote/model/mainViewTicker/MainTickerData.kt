package com.example.koin_apps.data.remote.model.mainViewTicker

import com.google.gson.annotations.SerializedName

data class MainTickerData(
    @SerializedName("CoinTitle")
    val coinTitle: String,

    @SerializedName("24H_Fluctate_Rate")
    val ticker_24H_FluctateRate: String,

    @SerializedName("24H_Fluctate")
    val ticker_24H_Fluctate: String
)