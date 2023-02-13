package com.example.koin_apps.data.remote.model.transaction

import com.google.gson.annotations.SerializedName

data class TransactionList(
    @SerializedName("Status")
    val status: String?,

    @SerializedName("Error_Message")
    val errorMsg: String,

    @SerializedName("transaction_Date")
    val transactionDate: String?,

    @SerializedName("transaction_Type")
    val transactionType: String?,

    @SerializedName("Units_Transaction_Traded")
    val units_Transaction_Traded: String?,

    @SerializedName("Transaction_Price")
    val transaction_Price: String?,

    @SerializedName("Transaction_Total")
    val transaction_Total: String?
    )