package com.koin.data.repository

import com.koin.model.orderbook.OrderRoot
import com.koin.model.ticker.TickerAllRoot
import com.koin.model.ticker.TickerRoot
import com.koin.model.transaction.TransactionRoot
import kotlinx.coroutines.flow.Flow

interface BithumbAPIRepository {
    fun getTickerAll(currency: String): Flow<TickerAllRoot>

    fun getTickerInfo(ticker: String, currency: String): Flow<TickerRoot>

    fun getTransactionHistory(
        ticker: String,
        currency: String,
        count: Int
    ): Flow<TransactionRoot>

    fun getOrderBook(
        ticker: String,
        currency: String,
        count: Int
    ): Flow<OrderRoot>
}