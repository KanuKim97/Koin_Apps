package com.example.domain.usecase.apiUseCase

import com.example.domain.entity.TickerOrderDataEntity
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
            closingPrice = it["closing_price"].toString().toInt(),
            prevClosingPrice = it["prev_closing_price"].toString().toInt(),
            maxPrice = it["max_price"].toString().toInt(),
            minPrice = it["min_price"].toString().toInt(),
            unitsTraded_24H = it["units_traded_24H"].toString().toDouble()
        )
    }
}