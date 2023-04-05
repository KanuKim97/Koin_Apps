package com.example.koin_apps.data.di.repository

import androidx.lifecycle.LiveData
import com.example.koin_apps.data.database.dao.TickerDao
import com.example.koin_apps.data.database.tables.TickerEntity
import javax.inject.Inject

/* CoinTitleDB Insert, Select, Delete Method Repository */
class TickerDBRepository @Inject constructor(
    private val tickerDao: TickerDao
) {
    val readAllTicker: LiveData<List<TickerEntity>> = tickerDao.readAllTicker()
    suspend fun insertTicker(ticker: TickerEntity): Unit = tickerDao.insertTicker(ticker)
    suspend fun deleteTicker(ticker: TickerEntity): Unit = tickerDao.deleteTicker(ticker)
}