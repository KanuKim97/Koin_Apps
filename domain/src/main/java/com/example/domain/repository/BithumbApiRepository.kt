package com.example.domain.repository

import com.example.domain.entity.OrderBookEntity
import com.example.domain.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface BithumbApiRepository {
    fun getTickerInfoAll(): Flow<MutableList<String>?>

    fun getTickerInfo(ticker: String): Flow<Map<String, Any>?>

    fun getTransactionHistory(ticker: String, count: Int): Flow<List<TransactionEntity>?>

    fun getOrderBookInfo(ticker: String, count: Int): Flow<OrderBookEntity?>
}