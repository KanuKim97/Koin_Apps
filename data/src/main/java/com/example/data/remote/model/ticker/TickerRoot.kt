package com.example.data.remote.model.ticker

data class TickerRoot(
    val status: String,
    val message: String?,
    val data: Map<String?, Any?>
)
