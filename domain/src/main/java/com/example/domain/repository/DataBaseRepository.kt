package com.example.domain.repository

import com.example.domain.entity.TickerEntity
import kotlinx.coroutines.flow.Flow

interface DataBaseRepository {
    fun readAllTicker(): Flow<List<TickerEntity>>

    fun insertTicker(ticker: TickerEntity): Flow<Result<Unit>>

    fun deleteTicker(ticker: TickerEntity): Flow<Result<Unit>>
}