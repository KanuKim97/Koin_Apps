package com.example.data.remote.model.orderbook

import com.google.gson.annotations.SerializedName

data class OrderRoot(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val orderData: OrderData?
)