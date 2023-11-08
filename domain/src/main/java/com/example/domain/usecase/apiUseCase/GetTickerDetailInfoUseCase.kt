package com.example.domain.usecase.apiUseCase

import com.example.domain.entity.api.ticker.TickerInfoViewEntity
import com.example.domain.repository.BithumbApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTickerDetailInfoUseCase @Inject constructor(
    private val apiRepo: BithumbApiRepository
) {
    operator fun invoke(ticker: String): Flow<TickerInfoViewEntity> =
        apiRepo.getTickerInfo(ticker).map {
            TickerInfoViewEntity(
                closingPrice = it?.closePrice?.toInt() ?: 0,
                prevClosingPrice = it?.prevClosingPrice?.toInt() ?: 0,
                minPrice = it?.minPrice?.toInt() ?: 0,
                maxPrice = it?.maxPrice?.toInt() ?: 0,
                tradedUnits24H = it?.tradedUnits24H?.toDouble() ?: 0.0
            )
        }
}