package com.example.koin_apps.data.di

import com.example.koin_apps.data.database.dao.CoinDao
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.remote.IKoinApiService
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val coinDao: CoinDao,
    private val koinApiService: IKoinApiService
    ) {
    val allCoinData = coinDao.readAllData()
    suspend fun addCoinList(coinEntity: CoinEntity) { coinDao.insertCoinTitle(coinEntity) }
    suspend fun deleteCoinList(coinEntity: CoinEntity) { coinDao.deleteCoinTitle(coinEntity) }

    /* Retrofit Client */
    suspend fun getTicker(path: String) = koinApiService.getTicker(path)
    suspend fun getTransaction(path: String, count:Int) = koinApiService.getTransactionHistory(path, count)
    suspend fun getOrderBook(path: String, count: Int) = koinApiService.getOrderBook(path, count)
}