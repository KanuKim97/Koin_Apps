package com.example.koin_apps.data.remote.model.transaction

data class TransactionList (
    var transactionDate: String,
    var transactionType: String,
    var units_Transaction_Traded: String,
    var transaction_Price: String,
    var transaction_Total: String
    )