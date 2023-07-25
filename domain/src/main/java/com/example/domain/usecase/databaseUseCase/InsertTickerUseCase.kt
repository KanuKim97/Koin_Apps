package com.example.domain.usecase.databaseUseCase

import com.example.domain.entity.db.TickerEntity
import com.example.domain.repository.DataBaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertTickerUseCase @Inject constructor(
    private val DBRepos: DataBaseRepository
) {
    operator fun invoke(ticker: TickerEntity): Flow<Result<Unit>> = DBRepos.insertTicker(ticker)
}