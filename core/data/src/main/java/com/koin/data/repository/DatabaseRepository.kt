package com.koin.data.repository

import com.koin.database.model.KoinTable
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    fun readAllTickers(): Flow<List<KoinTable>>

    fun insertTicker(ticker: KoinTable): Flow<Result<Unit>>

    fun deleteTicker(ticker: KoinTable): Flow<Result<Unit>>
}