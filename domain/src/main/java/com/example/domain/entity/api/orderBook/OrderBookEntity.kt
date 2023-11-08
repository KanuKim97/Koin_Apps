package com.example.domain.entity.api.orderBook

data class OrderBookEntity(
    val status: String?,
    val timestamp: String?,
    val orderCurrency: String?,
    val paymentCurrency: String?,
    val bids: List<OrderInfoEntity>?,
    val asks: List<OrderInfoEntity>?
)