package com.example.data.repositoryImpl

import android.database.SQLException
import com.example.data.database.dao.TickerDao
import com.example.data.database.tables.TickerTables
import com.example.data.util.toTickerEntityMapper
import com.example.data.util.toTickerTableMapper
import com.example.domain.entity.db.TickerEntity
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
        val result: List<TickerEntity> = toTickerEntityMapper(tickerDao.readAllTicker())
        emit(result)
    }.retryWhen { cause: Throwable, attempt: Long ->
        if (cause is SQLException && attempt < 3L) {
            delay(2000L)
            true
        } else {
            false
        }
    }.catch { cause ->
        when (cause) {
            is IOException -> emit(listOf())
            is SQLDataException -> emit(listOf())
            is ClassNotFoundException -> emit(listOf())
            else -> throw Exception()
        }
    }

    override fun insertTicker(ticker: TickerEntity): Flow<Result<Unit>> = flow {
        val tickerInputData: TickerTables = toTickerTableMapper(ticker)

        try {
            tickerDao.insertTicker(tickerInputData)
            emit(Result.success(Unit))
        } catch (exception: Exception) {
            throw exception
        }
    }.retryWhen { cause: Throwable, attempt: Long ->
        if (cause is SQLException && attempt < 3L) {
            delay(2000L)
            true
        } else {
            false
        }
    }.catch { cause ->
        when (cause) {
            is IOException -> emit(Result.failure(cause))
            is SQLDataException -> emit(Result.failure(cause))
            is ClassNotFoundException -> emit(Result.failure(cause))
            else -> throw Exception()
        }
    }

    override fun deleteTicker(ticker: TickerEntity): Flow<Result<Unit>> = flow {
        val tickerInputData: TickerTables = toTickerTableMapper(ticker)

        try {
            tickerDao.deleteTicker(tickerInputData)
            emit(Result.success(Unit))
        } catch (exception: Exception) {
            throw exception
        }
    }.retryWhen { cause: Throwable, attempt: Long ->
        if (cause is SQLException && attempt < 3L) {
            delay(2000L)
            true
        } else {
            false
        }
    }.catch { cause ->
        when (cause) {
            is IOException -> emit(Result.failure(cause))
            is SQLDataException -> emit(Result.failure(cause))
            is ClassNotFoundException -> emit(Result.failure(cause))
            else -> throw Exception()
        }
    }
}