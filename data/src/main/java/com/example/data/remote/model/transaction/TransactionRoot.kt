package com.example.data.remote.model.transaction

import com.google.gson.annotations.SerializedName

data class TransactionRoot(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val transactionData: ArrayList<TransactionData?>
)
