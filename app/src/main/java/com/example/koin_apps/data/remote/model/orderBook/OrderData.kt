package com.example.koin_apps.data.remote.model.orderBook

data class OrderData(
    val status: String?,
    val ErrorMsg: String?,

    val timestamp: String?,
    val order_currency: String?,
    val payment_currency: String?,
    val bids: ArrayList<OrderAskBidData>?,
    val asks: ArrayList<OrderAskBidData>?
)