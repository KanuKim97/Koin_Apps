package com.example.domain.usecase.apiUseCase

import com.example.domain.entity.api.ticker.TickerLiveDataEntity
import com.example.domain.repository.BithumbApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTickerLiveInfoUseCase @Inject constructor(
    private val bithumbApiRepo: BithumbApiRepository
) {
    operator fun invoke(
        ticker: String
    ): Flow<TickerLiveDataEntity> = bithumbApiRepo.getTickerInfo(ticker).map {
        TickerLiveDataEntity(
            closingPrice = it?.closePrice?.toInt() ?: 0,
            fluctate_24H = it?.fluctuatePrice?.toInt() ?: 0,
            fluctate_Rate = it?.fluctuateRate?.toDouble() ?: 0.0
        )
    }
}