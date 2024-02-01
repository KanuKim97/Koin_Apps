package com.koin.model.ticker

data class TickerRoot(
    val status: String,
    val message: String?,
    val data: TickerData? = null
)