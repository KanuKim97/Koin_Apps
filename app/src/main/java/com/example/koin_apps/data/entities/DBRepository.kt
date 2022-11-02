package com.example.koin_apps.data.entities

import androidx.lifecycle.LiveData
import com.example.koin_apps.data.entities.db.CoinEntity
import com.example.koin_apps.data.entities.remote.CoinDao

class DBRepository(private val coinDao: CoinDao) {
    val readAllData: LiveData<List<CoinEntity>> = coinDao.readAllData()

    fun addUser(coinEntity: CoinEntity) {
        coinDao.insertSelected(coinEntity)
    }
}