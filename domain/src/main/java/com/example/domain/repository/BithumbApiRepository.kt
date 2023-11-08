package com.example.domain.repository

interface BithumbApiRepository {
    fun getTickerInfoAll()

    fun getTickerInfo(ticker: String)

    fun getTransactionHistory(ticker: String, count: Int)

    fun getOrderBookInfo(ticker: String, count: Int)
}