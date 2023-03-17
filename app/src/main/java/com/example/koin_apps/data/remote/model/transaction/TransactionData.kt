package com.example.koin_apps.data.remote.model.transaction

data class TransactionData(
    val transaction_date: String,
    val type: String,
    val units_traded: String,
    val price: String,
    val total: String
)