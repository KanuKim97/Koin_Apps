package com.example.domain.usecase.apiUseCase

import com.example.domain.entity.api.transaction.TransactionEntity
import com.example.domain.repository.BithumbApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionHistoryUseCase @Inject constructor(private val apiRepo: BithumbApiRepository) {
    operator fun invoke(
        ticker: String,
        count: Int
    ): Flow<List<TransactionEntity>?> = apiRepo.getTransactionHistory(ticker, count)
}