package com.koin.domain

import com.koin.data.repository.BithumbAPIRepository
import com.koin.model.transaction.TransactionRoot
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionHistoryUseCase @Inject constructor(
    private val koinNetwork: BithumbAPIRepository
) {
    operator fun invoke(
        ticker: String,
        currency: String,
        count: Int
    ): Flow<TransactionRoot> = koinNetwork.getTransactionHistory(ticker, currency, count)
}