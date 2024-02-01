package com.koin.model.ticker


data class TickerAllRoot(
    val status: String,
    val message: String?,
    val data: Map<String, Any>?
)