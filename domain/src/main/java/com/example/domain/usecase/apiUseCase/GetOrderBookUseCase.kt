package com.example.domain.usecase.apiUseCase

import com.example.domain.entity.api.orderBook.OrderBookEntity
import com.example.domain.repository.BithumbApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrderBookUseCase @Inject constructor(
    private val apiRepo: BithumbApiRepository
) {
    operator fun invoke(
        ticker: String,
        count: Int
    ): Flow<OrderBookEntity?> = apiRepo.getOrderBookInfo(ticker, count)
}