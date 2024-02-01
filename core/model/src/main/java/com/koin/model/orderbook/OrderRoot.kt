package com.koin.model.orderbook

data class OrderRoot(
    val status: String,
    val message: String?,
    val data: OrderData? = null
)