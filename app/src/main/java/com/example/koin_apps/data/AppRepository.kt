package com.example.koin_apps.data

import com.example.koin_apps.data.database.dao.CoinDao
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.RetroRepo


class AppRepository(private val coinDao: CoinDao) {

    fun readAllData() = coinDao.readAllData()

    suspend fun addCoinList(coinEntity: CoinEntity) { coinDao.insertCoinTitle(coinEntity) }

    suspend fun deleteCoinList(coinEntity: CoinEntity) { coinDao.deleteCoinTitle(coinEntity) }

    /* Retrofit Client */
    private val coinApiClient: IKoinApiService =
        RetroRepo.getClient().create(IKoinApiService::class.java)

    suspend fun getTicker(path: String) = coinApiClient.getTicker(path)

    suspend fun getTransaction(path: String, count:Int) = coinApiClient.getTransactionHistory(path, count)

    fun getOrderBook(path: String, count: Int) = coinApiClient.getOrderBook(path, count)
}