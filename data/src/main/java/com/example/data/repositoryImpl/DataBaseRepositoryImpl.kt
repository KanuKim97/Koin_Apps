package com.example.data.repositoryImpl

import android.database.SQLException
import com.example.data.database.dao.TickerDao
import com.example.data.util.databaseMapper.mapperToTickerEntity
import com.example.data.util.databaseMapper.mapperToTickerTable
import com.example.domain.entity.TickerEntity
import com.example.domain.repository.DataBaseRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.io.IOException
import java.sql.SQLDataException
import javax.inject.Inject

class DataBaseRepositoryImpl @Inject constructor(
    private val tickerDao: TickerDao
): DataBaseRepository {
    override fun readAllTicker(): Flow<List<TickerEntity>> = flow {
        val tickerResults = mapperToTickerEntity(tickerDao.readAllTicker())
        emit(tickerResults)
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(listOf())
            is SQLDataException -> emit(listOf())
            is ClassNotFoundException -> emit(listOf())
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        if (cause is SQLException && attempt < 3) {
            delay(1000L)
            true
        } else {
            false
        }
    }

    override fun insertTicker(ticker: TickerEntity): Flow<Result<Unit>> = flow {
        val tickerInput = mapperToTickerTable(ticker)
        tickerDao.insertTicker(tickerInput)
        emit(Result.success(Unit))
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(Result.failure(exception))
            is SQLDataException -> emit(Result.failure(exception))
            is ClassNotFoundException -> emit(Result.failure(exception))
            else -> throw exception
        }
    }.retryWhen { cause, attempt ->
        if (cause is SQLException && attempt < 3) {
            delay(1000L)
            true
        } else {
            false
        }
    }

    override fun deleteTicker(ticker: TickerEntity): Flow<Result<Unit>> = flow {
        val tickerInput = mapperToTickerTable(ticker)
        tickerDao.deleteTicker(tickerInput)
        emit(Result.success(Unit))
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(Result.failure(exception))
            is SQLDataException -> emit(Result.failure(exception))
            is ClassNotFoundException -> emit(Result.failure(exception))
            else -> throw exception
        }
    }.retryWhen {  cause, attempt ->
        if (cause is SQLException && attempt < 3) {
            delay(1000L)
            true
        } else {
            false
        }
    }
}