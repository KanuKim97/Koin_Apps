package com.example.koin_apps.data.remote.model.orderBook

data class OrderRoot(
    val status: String,
    val message: String?,
    val data: OrderData?
)