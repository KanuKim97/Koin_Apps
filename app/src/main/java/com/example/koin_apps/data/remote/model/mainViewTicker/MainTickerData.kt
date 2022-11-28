package com.example.koin_apps.data.remote.model.mainViewTicker

import com.google.gson.annotations.SerializedName

data class MainTickerData(
    @SerializedName("CoinTitle")
    val coinTitle: String,

    @SerializedName("24H_Fluctate_Rate")
    val ticker_24H_FluctateRate: String,

    @SerializedName("24H_Fluctate")
    val ticker_24H_Fluctate: String,

    @SerializedName("Prev_Closing_Price")
    val ticker_Prev_Closing_Price: String,

    @SerializedName("24H_Acc_Trade_Value")
    val ticker_24H_Acc_Trade_Value: String,

    @SerializedName("24H_Units_Traded")
    val ticker_24H_Units_Traded: String
)
