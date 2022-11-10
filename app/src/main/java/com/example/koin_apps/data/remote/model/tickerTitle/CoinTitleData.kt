package com.example.koin_apps.data.remote.model.tickerTitle

import com.google.gson.annotations.SerializedName

data class CoinTitleData(
    @SerializedName("CoinTitle")
    val tickerTitle: String?,
    @SerializedName("isChecked")
    val isChecked: Boolean
)
