package com.koin.domain

import com.koin.data.repository.DatabaseRepository
import com.koin.database.model.KoinTable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAllTickerUseCase @Inject constructor(
    private val koinDao: DatabaseRepository
) {
    operator fun invoke(): Flow<List<KoinTable>> = koinDao.readAllTickers()
}