package com.koin.model.transaction


data class TransactionRoot(
    val status: String,
    val message: String?,
    val data: ArrayList<TransactionData> = arrayListOf()
)
