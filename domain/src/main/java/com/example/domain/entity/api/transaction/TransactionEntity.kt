package com.example.domain.entity.api.transaction

data class TransactionEntity(
    val transaction_date: String,
    val type: String,
    val units_traded: String,
    val price: String,
    val total: String
)