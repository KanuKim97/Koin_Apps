package com.example.koin_apps.data.remote.model.transaction

data class TransactionRoot(
    val status: String,
    val message: String?,
    val data: ArrayList<TransactionData>?
)