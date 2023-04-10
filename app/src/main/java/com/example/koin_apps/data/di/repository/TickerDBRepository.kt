package com.example.koin_apps.data.di.repository

import com.example.koin_apps.data.database.tables.TickerEntity
import com.example.koin_apps.data.di.dataSource.TickerDBDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * TickerDataBase Insert, Select, Delete Method Repository (Coroutine Flow Intermediary)
 *  1. readAllTicker() : SELECT : Get All CryptoCurrency Ticker from TickerDataBase
 *  2. insertTicker() : INSERT : Insert CryptoCurrency Ticker into TickerDataBase
 *  3. deleteTicker() : DELETE : Delete CryptoCurrency Ticker from TickerDataBase
 * */

class TickerDBRepository @Inject constructor(
    private val tickerDBDataSource: TickerDBDataSource
) {
    fun readAllTicker(): Flow<List<TickerEntity>> = tickerDBDataSource.readAllTicker()

    fun insertTicker(ticker: TickerEntity): Flow<Result<Unit>> =
        tickerDBDataSource.insertTicker(ticker)

    fun deleteTicker(ticker: TickerEntity): Flow<Result<Unit>> =
        tickerDBDataSource.deleteTicker(ticker)
}