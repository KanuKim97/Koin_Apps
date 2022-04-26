package com.example.koin_apps.data.remote.model.transaction

data class TransactionList (
    var openTransactionData: Int,
    var closeTransactionData: Int,
    var minTransactionPrice: Int,
    var maxTransactionPrice: Int,
    var transactionTradedUnits: Double,
    var transactionTradedValue: Double,
    var prevTransactionClosePrice: Int,
    var transactionTraded_Units_24H: Double,
    var transactionTraded_value_24H: Double,
    var transactionFluctated_24H: Int,
    var transactionFluctated_Rate_24H: Double,
    var transactionDate: Double
    )