package com.example.data.remote.model.orderbook

data class OrderData(
    val status: String?,
    val timestamp: String?,
    val order_currency: String?,
    val payment_currency: String?,
    val bids: ArrayList<OrderAskBidData>?,
    val asks: ArrayList<OrderAskBidData>?
)