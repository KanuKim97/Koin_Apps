package com.example.koin_apps.data.di.repository

import com.example.koin_apps.data.remote.IKoinApiService
import javax.inject.Inject

/* Bithumb public Api Repository */
class ApiRepository @Inject constructor(private val koinApiService: IKoinApiService) {
    suspend fun getTickerAll() = koinApiService.getTickerALL()
    suspend fun getTicker(path: String) = koinApiService.getTicker(path)
    suspend fun getTransactionHistory(path: String, count: Int) =
        koinApiService.getTransactionHistory(path, count)
    suspend fun getOrderBook(path: String, count: Int) =
        koinApiService.getOrderBook(path, count)
}