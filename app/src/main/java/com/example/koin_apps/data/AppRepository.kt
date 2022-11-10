package com.example.koin_apps.data

import androidx.lifecycle.LiveData
import com.example.koin_apps.data.database.dao.CoinDao
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.RetroRepo


class AppRepository(private val coinDao: CoinDao) {


    /* Room DataBase */
    val readAllData: LiveData<List<CoinEntity>> = coinDao.readAllData()

    fun addUser(coinEntity: CoinEntity) {
        coinDao.insertCoinTitle(coinEntity)
    }

    /* Retrofit Client */
    private val coinApiClient: IKoinApiService =
        RetroRepo.getClient().create(IKoinApiService::class.java)

    fun getTicker(path: String) = coinApiClient.getTicker(path)

    fun getTransaction(path: String, count:Int) = coinApiClient.getTransactionHistory(path, count)

    fun getOrderBook(path: String, count: Int) = coinApiClient.getOrderBook(path, count)
}