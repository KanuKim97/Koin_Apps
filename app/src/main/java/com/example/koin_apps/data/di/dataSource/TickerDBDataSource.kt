package com.example.koin_apps.data.di.dataSource

import android.database.SQLException
import androidx.lifecycle.LiveData
import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.database.dao.TickerDao
import com.example.koin_apps.data.database.tables.TickerEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException
import java.sql.SQLDataException
import javax.inject.Inject

/**
 * Ticker DataBase Select, Insert, Delete Method (Coroutine Flow Producer)
 *  1. readAllTicker() : SELECT : Get All CryptoCurrency Ticker from TickerDataBase
 *  2. insertTicker() : INSERT : Insert CryptoCurrency Ticker into TickerDataBase
 *  3. deleteTicker() : DELETE : Delete CryptoCurrency Ticker from TickerDataBase
 * */
class TickerDBDataSource @Inject constructor(
    private val tickerDAO: TickerDao
) {
    fun readAllTicker(): Flow<List<TickerEntity>> = flow {
        emit(tickerDAO.readAllTicker())
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(listOf())
            is ClassNotFoundException -> emit(listOf())
            is SQLDataException -> emit(listOf())
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        if (cause is SQLException && attempt < Constants.RETRY_MAX_ATTEMPT) {
            delay(Constants.DELAY_TIME_MILLIS)
            true
        } else {
            false
        }
    }

    fun insertTicker(ticker: TickerEntity): Flow<Result<Unit>> = flow {
        tickerDAO.insertTicker(ticker)
        emit(Result.success(Unit))
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(Result.failure(exception))
            is ClassNotFoundException -> emit(Result.failure(exception))
            is SQLDataException -> emit(Result.failure(exception))
            else -> throw exception
        }
    }.retryWhen {cause, attempt ->
        if (cause is SQLException && attempt < Constants.RETRY_MAX_ATTEMPT) {
            delay(Constants.DELAY_TIME_MILLIS)
            true
        } else {
            false
        }
    }

    fun deleteTicker(ticker: TickerEntity): Flow<Result<Unit>> = flow {
        tickerDAO.deleteTicker(ticker)
        emit(Result.success(Unit))
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(Result.failure(exception))
            is ClassNotFoundException -> emit(Result.failure(exception))
            is SQLDataException -> emit(Result.failure(exception))
            else -> throw exception
        }
    }.retryWhen {cause, attempt ->
        if (cause is SQLException && attempt < Constants.RETRY_MAX_ATTEMPT) {
            delay(Constants.DELAY_TIME_MILLIS)
            true
        } else {
            false
        }
    }

}