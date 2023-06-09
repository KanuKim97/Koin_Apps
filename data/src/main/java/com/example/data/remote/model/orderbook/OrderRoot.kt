package com.example.data.remote.model.orderbook

data class OrderRoot(
    val status: String,
    val message: String?,
    val data: OrderData?
)