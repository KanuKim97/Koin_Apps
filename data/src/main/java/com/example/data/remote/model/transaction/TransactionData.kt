package com.example.data.remote.model.transaction

import com.google.gson.annotations.SerializedName

data class TransactionData(
    @SerializedName("transaction_date") val transactionDate: String,
    @SerializedName("type") val transactionType: String,
    @SerializedName("units_traded") val tradedUnit: String,
    @SerializedName("price") val transactionPrice: String,
    @SerializedName("total") val transactionTotalPrice: String
)