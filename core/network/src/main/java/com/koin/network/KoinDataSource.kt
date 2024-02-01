package com.koin.network

import com.koin.model.orderbook.OrderRoot
import com.koin.model.ticker.TickerAllRoot
import com.koin.model.ticker.TickerRoot
import com.koin.model.transaction.TransactionRoot


interface KoinDataSource {

    suspend fun getTickerAll(currency: String): TickerAllRoot

    suspend fun getTickerInfo(
        ticker: String,
        currency: String
    ): TickerRoot

    suspend fun getTransactionHistory(
        ticker: String,
        currency: String,
        count: Int
    ): TransactionRoot

    suspend fun getOrderBook(
        ticker: String,
        currency: String,
        count: Int
    ): OrderRoot
}