package com.example.koin_apps.data.remote.model.tickerTitle

import com.google.gson.annotations.SerializedName

data class TickerTitleData(
    @SerializedName("tickerTitle")
    val tickerTitle: String,

    @SerializedName("isChecked")
    val checked: Boolean
)
