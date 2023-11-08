package com.example.domain.entity.api.transaction

data class TransactionEntity(
    val transactionDate: String,
    val type: String,
    val tradedUnits: String,
    val price: String,
    val total: String
)