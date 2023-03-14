package com.example.koin_apps.data.di.repository

import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.model.orderBook.OrderRoot
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import retrofit2.Response
import javax.inject.Inject

/* Bithumb public Api Repository */
class ApiRepository @Inject constructor(private val koinApiService: IKoinApiService) {
    suspend fun getTickerAll(): Response<TickerRoot> = koinApiService.getTickerALL()
    suspend fun getTicker(path: String): Response<TickerRoot> = koinApiService.getTicker(path)
    suspend fun getTransactionHistory(path: String, count: Int): Response<TransactionRoot> =
        koinApiService.getTransactionHistory(path, count)
    suspend fun getOrderBook(path: String, count: Int): Response<OrderRoot> =
        koinApiService.getOrderBook(path, count)
}