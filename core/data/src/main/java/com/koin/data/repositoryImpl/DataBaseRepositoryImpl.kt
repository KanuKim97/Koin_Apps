package com.koin.data.repositoryImpl

import com.koin.common.CoreDispatcher
import com.koin.common.Dispatcher
import com.koin.data.repository.DatabaseRepository
import com.koin.database.dao.KoinDao
import com.koin.database.model.KoinTable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import java.sql.SQLDataException
import javax.inject.Inject

class DataBaseRepositoryImpl @Inject constructor(
    private val dao: KoinDao,
    @CoreDispatcher(Dispatcher.IO) private val ioDispatcher: CoroutineDispatcher
): DatabaseRepository {
    override fun readAllTickers(): Flow<List<KoinTable>> = flow {
        val result = dao.readAllTicker()
        emit(result)
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(listOf())
            is SQLDataException -> emit(listOf())
            is ClassNotFoundException -> emit(listOf())
            else -> emit(listOf())
        }
    }.flowOn(ioDispatcher)

    override fun insertTicker(ticker: KoinTable): Flow<Result<Unit>> = flow {
        dao.insertTicker(ticker)
        emit(Result.success(Unit))
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(Result.failure(exception))
            is SQLDataException -> emit(Result.failure(exception))
            is ClassNotFoundException -> emit(Result.failure(exception))
            else -> emit(Result.failure(exception))
        }
    }.flowOn(ioDispatcher)

    override fun deleteTicker(ticker: KoinTable): Flow<Result<Unit>> = flow {
        dao.deleteTicker(ticker)
        emit(Result.success(Unit))
    }.catch { exception ->
        when (exception) {
            is IOException -> emit(Result.failure(exception))
            is SQLDataException -> emit(Result.failure(exception))
            is ClassNotFoundException -> emit(Result.failure(exception))
            else -> emit(Result.failure(exception))
        }
    }.flowOn(ioDispatcher)
}