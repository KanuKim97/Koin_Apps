package com.example.domain.usecase.databaseUseCase

import com.example.domain.entity.TickerEntity
import com.example.domain.repository.DataBaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAllTickerUseCase @Inject constructor(
    private val DBRepos: DataBaseRepository
) {
    operator fun invoke(): Flow<List<TickerEntity>> = DBRepos.readAllTicker()
}