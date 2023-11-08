package com.example.domain.repository

import com.example.domain.entity.api.orderBook.OrderBookEntity
import com.example.domain.entity.api.ticker.TickerInfoDetailEntity
import com.example.domain.entity.api.transaction.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface BithumbApiRepository {
    fun getTickerInfoAll(): Flow<MutableList<String>?>

    fun getTickerInfo(ticker: String): Flow<TickerInfoDetailEntity?>

    fun getTransactionHistory(ticker: String, count: Int): Flow<List<TransactionEntity>?>

    fun getOrderBookInfo(ticker: String, count: Int): Flow<OrderBookEntity?>
}