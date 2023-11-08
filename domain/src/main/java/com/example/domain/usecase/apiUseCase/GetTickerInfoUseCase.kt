package com.example.domain.usecase.apiUseCase

import com.example.domain.entity.api.ticker.TickerInfoEntity
import com.example.domain.repository.BithumbApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTickerInfoUseCase @Inject constructor(
    private val apiRepo: BithumbApiRepository
) {
    operator fun invoke(ticker: String): Flow<TickerInfoEntity> =
        apiRepo.getTickerInfo(ticker).map {
            TickerInfoEntity(
                closingPrice = it?.closePrice?.toInt() ?: 0,
                fluctate24H = it?.fluctuatePrice?.toInt() ?: 0,
                fluctateRate = it?.fluctuateRate?.toDouble() ?: 0.0
            )
        }
}