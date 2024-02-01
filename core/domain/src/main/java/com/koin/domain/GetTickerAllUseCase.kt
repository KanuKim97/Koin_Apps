package com.koin.domain

import com.koin.data.repository.BithumbAPIRepository
import com.koin.model.ticker.TickerAllRoot
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTickerAllUseCase @Inject constructor(
    private val koinNetwork: BithumbAPIRepository
) {
    operator fun invoke(currency: String): Flow<TickerAllRoot> =
        koinNetwork.getTickerAll(currency)
}