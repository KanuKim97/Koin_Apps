package com.example.koin_apps.data.remote.model.ticker

data class TickerRoot(
    val status: String?,
    val data: Map<String?, Any?>,
    val message: String?
)