package com.example.koin_apps.data.di.repository

import com.example.koin_apps.data.di.dataSource.CoinRemoteDataSource
import com.example.koin_apps.data.remote.model.ticker.LiveTickerData
import com.example.koin_apps.data.remote.model.ticker.OrderTickerData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/* Coroutine Flow Intermediary -> Convert Bithumb Public API Result */
class CoinRepository @Inject constructor(
    private val coinRemoteDataSource: CoinRemoteDataSource
) {
    fun getTickerInfoALL() = coinRemoteDataSource.getTickerInfoAll()

    fun getTickerInfoLive(ticker: String) = coinRemoteDataSource.getTickerInfo(ticker)
        .map { result ->
            LiveTickerData(
                result?.get("closing_price").toString(),
                result?.get("fluctate_24H").toString(),
                result?.get("fluctate_rate_24H").toString()
            )
        }

    fun getTickerInfoDetail(ticker: String) = coinRemoteDataSource.getTickerInfo(ticker)
        .map { result ->
            OrderTickerData(
                result?.get("closing_price").toString(),
                result?.get("prev_closing_price").toString(),
                result?.get("max_price").toString(),
                result?.get("min_price").toString(),
                result?.get("units_traded_24H").toString()
            )
        }

    fun getTransactionInfo(ticker: String, count: Int) =
        coinRemoteDataSource.getTransactionInfo(ticker, count)

    fun getOrderBookInfo(ticker: String, count: Int) =
        coinRemoteDataSource.getOrderBookInfo(ticker, count)
}