package com.example.domain.usecase.apiUseCase

import com.example.domain.entity.api.ticker.TickerOrderDataEntity
import com.example.domain.repository.BithumbApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTickerDetailInfoUseCase @Inject constructor(
    private val bithumbApiRepo: BithumbApiRepository
) {
    operator fun invoke(
        ticker: String
    ): Flow<TickerOrderDataEntity> = bithumbApiRepo.getTickerInfo(ticker).map {
        TickerOrderDataEntity(
            closingPrice = it?.closePrice?.toInt() ?: 0,
            prevClosingPrice = it?.prevClosingPrice?.toInt() ?: 0,
            minPrice = it?.minPrice?.toInt() ?: 0,
            maxPrice = it?.maxPrice?.toInt() ?: 0,
            unitsTraded_24H = it?.tradedUnits_24H?.toDouble() ?: 0.0,
        )
    }
}