package com.example.domain.entity.api.orderBook

data class OrderBookEntity(
    val status: String?,
    val timestamp: String?,
    val order_currency: String?,
    val payment_currency: String?,
    val bids: List<OrderInfoEntity>?,
    val asks: List<OrderInfoEntity>?
)