package com.koin.domain

import com.koin.data.repository.BithumbAPIRepository
import com.koin.model.orderbook.OrderRoot
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrderBookUseCase @Inject constructor(
    private val koinNetwork: BithumbAPIRepository
) {
    operator fun invoke(
        ticker: String,
        currency: String,
        count: Int
    ): Flow<OrderRoot> = koinNetwork.getOrderBook(ticker, currency, count)
}