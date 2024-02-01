package com.koin.domain

import com.koin.data.repository.BithumbAPIRepository
import com.koin.model.ticker.TickerRoot
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTickerInfoUseCase @Inject constructor(
    private val koinNetwork: BithumbAPIRepository
) {
    operator fun invoke(
        ticker: String,
        currency: String
    ): Flow<TickerRoot> = koinNetwork.getTickerInfo(ticker = ticker, currency = currency)
}