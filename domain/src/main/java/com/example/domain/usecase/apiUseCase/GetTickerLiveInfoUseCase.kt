package com.example.domain.usecase.apiUseCase

import com.example.domain.entity.TickerLiveDataEntity
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
            closingPrice = it["closing_price"].toString().toInt(),
            fluctate_24H = it["fluctate_24H"].toString().toInt(),
            fluctate_Rate = it["fluctate_rate_24H"].toString().toDouble()
        )
    }
}