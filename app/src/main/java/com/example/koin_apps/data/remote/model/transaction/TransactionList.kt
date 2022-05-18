package com.example.koin_apps.data.remote.model.transaction

import com.google.gson.annotations.SerializedName

data class TransactionList (

    @SerializedName("transactionDate")
    var transactionDate: String,

    @SerializedName("transactionType")
    var transactionType: String,

    @SerializedName("Units_Transaction_Traded")
    var units_Transaction_Traded: String,

    @SerializedName("Transaction_Price")
    var transaction_Price: String,

    @SerializedName("Transaction_Total")
    var transaction_Total: String
    )