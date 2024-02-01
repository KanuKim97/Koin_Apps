package com.koin.model.orderbook

data class OrderData(
    val time_stamp: Long,
    val payment_currency: String,
    val order_currency: String,
    val bids: ArrayList<OrderInfo>,
    val asks: ArrayList<OrderInfo>
)