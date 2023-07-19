package com.example.domain.entity

data class OrderBookEntity(
    val status: String?,
    val timestamp: String?,
    val order_currency: String?,
    val payment_currency: String?,
    val bids: List<OrderAskBidEntity>?,
    val asks: List<OrderAskBidEntity>?
)