package com.example.koin_apps.data.remote.model.tickerTitle

import com.google.gson.annotations.SerializedName

data class KoinTitleData(
    @SerializedName("tickerTitle")
    val tickerTitle: String
)
