package com.example.data.remote.model.orderbook

import com.google.gson.annotations.SerializedName

data class OrderData(
    @SerializedName("timestamp") val timeStamp: Long,
    @SerializedName("payment_currency") val paymentCurrency: String,
    @SerializedName("order_currency") val orderCurrency: String,
    @SerializedName("bids") val orderBids: ArrayList<OrderInfo>,
    @SerializedName("asks") val orderAsks: ArrayList<OrderInfo>
)