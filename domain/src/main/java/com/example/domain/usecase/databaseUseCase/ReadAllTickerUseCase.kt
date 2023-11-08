package com.example.domain.usecase.databaseUseCase

import com.example.domain.entity.db.TickerEntity
import com.example.domain.repository.DataBaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAllTickerUseCase @Inject constructor(private val dbRepos: DataBaseRepository) {
    operator fun invoke(): Flow<List<TickerEntity>> = dbRepos.readAllTicker()
}