package com.example.koin_apps.data.di.repository

import com.example.koin_apps.data.database.dao.CoinDao
import com.example.koin_apps.data.database.tables.CoinEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/* CoinTitleDB Insert, Select, Delete Method Repository */
class CoinTitleDBRepository @Inject constructor(
    private val coinDao: CoinDao
) {
    val readAllCoinTitle = coinDao.readAllData()
    suspend fun insertCoinTitle(coinTitle: CoinEntity) = coinDao.insertCoinTitle(coinTitle)
    suspend fun deleteCoinTitle(coinTitle: CoinEntity) = coinDao.deleteCoinTitle(coinTitle)
}