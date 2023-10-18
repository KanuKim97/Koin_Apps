package com.example.data.remote.model.orderbook

import com.google.gson.annotations.SerializedName

data class OrderInfo(
    @SerializedName("price") val orderPrice: String,
    @SerializedName("quantity") val orderQuantity: String
)