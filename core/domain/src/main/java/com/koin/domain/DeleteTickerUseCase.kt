package com.koin.domain

import com.koin.data.repository.DatabaseRepository
import com.koin.database.model.KoinTable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteTickerUseCase @Inject constructor(
    private val koinDao: DatabaseRepository
) {
    operator fun invoke(
        ticker: KoinTable
    ): Flow<Result<Unit>> = koinDao.deleteTicker(ticker)
}