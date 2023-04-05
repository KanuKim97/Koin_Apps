package com.example.koin_apps.data.di.repository

import android.icu.text.DecimalFormat
import com.example.koin_apps.data.di.dataSource.TickerRemoteDataSource
import com.example.koin_apps.data.remote.model.ticker.LiveTickerData
import com.example.koin_apps.data.remote.model.ticker.OrderTickerData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/* Coroutine Flow Intermediary -> Convert Bithumb Public API Result */
class TickerRepository @Inject constructor(
    private val tickerRemoteDataSource: TickerRemoteDataSource
) {
    private val wonFormat = DecimalFormat("###,###")
    private val unitsFormat = DecimalFormat("##.##")

    fun getTickerInfoALL() = tickerRemoteDataSource.getTickerInfoAll()

    fun getTickerInfoLive(ticker: String) = tickerRemoteDataSource.getTickerInfo(ticker)
        .map { result ->
            LiveTickerData(
                wonFormat.format(result?.get("closing_price").toString().toInt()),
                wonFormat.format(result?.get("fluctate_24H").toString().toInt()),
                unitsFormat.format(result?.get("fluctate_rate_24H").toString().toDouble())
            )
        }

    fun getTickerInfoDetail(ticker: String) = tickerRemoteDataSource.getTickerInfo(ticker)
        .map { result ->
            OrderTickerData(
                wonFormat.format(result?.get("closing_price").toString().toInt()),
                wonFormat.format(result?.get("prev_closing_price").toString().toInt()),
                wonFormat.format(result?.get("max_price").toString().toInt()),
                wonFormat.format(result?.get("min_price").toString().toInt()),
                unitsFormat.format(result?.get("units_traded_24H").toString().toDouble())
            )
        }

    fun getTransactionInfo(ticker: String, count: Int) =
        tickerRemoteDataSource.getTransactionInfo(ticker, count)

    fun getOrderBookInfo(ticker: String, count: Int) =
        tickerRemoteDataSource.getOrderBookInfo(ticker, count)
}