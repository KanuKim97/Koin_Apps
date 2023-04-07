package com.example.koin_apps.data.di.repository

import android.icu.text.DecimalFormat
import com.example.koin_apps.data.di.dataSource.TickerRemoteDataSource
import com.example.koin_apps.data.remote.model.orderBook.OrderData
import com.example.koin_apps.data.remote.model.ticker.LiveTickerData
import com.example.koin_apps.data.remote.model.ticker.OrderTickerData
import com.example.koin_apps.data.remote.model.transaction.TransactionData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Convert Bithumb Public API Result (Coroutine Flow Intermediary)
 *  1. getTickerInfoAll()
 *   - Get All CryptoCurrency Ticker Code
 *  2. getTickerInfoLive() :
 *   - parameter : ticker(String)
 *   - Get Ticker closingPrice, Fluctate_24H, Fluctate_Rate_24H
 *  3. getTickerInfoDetail() :
 *   - parameter : ticker(String)
 *   - Get Ticker closingPrice, PrevClosingPrice, MaxPrice, MinPrice, UnitsTraded24H
 *  4. getTransactionInfo() :
 *   - parameter : ticker(String), count(Int)
 *   - Get Transaction Data
 *  5. getOrderBookInfo() :
 *   - parameter : ticker(String), count(Int)
 *   - Get Transaction Data
 * */
class TickerRepository @Inject constructor(
    private val tickerRemoteDataSource: TickerRemoteDataSource
) {
    private val wonFormat = DecimalFormat("###,###")
    private val unitsFormat = DecimalFormat("##.##")

    fun getTickerInfoALL(): Flow<MutableList<String?>> = tickerRemoteDataSource.getTickerInfoAll()

    fun getTickerInfoLive(ticker: String): Flow<LiveTickerData> =
        tickerRemoteDataSource.getTickerInfo(ticker).map { result ->
            LiveTickerData(
                wonFormat.format(result?.get("closing_price").toString().toInt()),
                wonFormat.format(result?.get("fluctate_24H").toString().toInt()),
                unitsFormat.format(result?.get("fluctate_rate_24H").toString().toDouble())
            )
        }

    fun getTickerInfoDetail(ticker: String): Flow<OrderTickerData> =
        tickerRemoteDataSource.getTickerInfo(ticker).map { result ->
            OrderTickerData(
                wonFormat.format(result?.get("closing_price").toString().toInt()),
                wonFormat.format(result?.get("prev_closing_price").toString().toInt()),
                wonFormat.format(result?.get("max_price").toString().toInt()),
                wonFormat.format(result?.get("min_price").toString().toInt()),
                unitsFormat.format(result?.get("units_traded_24H").toString().toDouble())
            )
        }

    fun getTransactionInfo(ticker: String, count: Int): Flow<ArrayList<TransactionData>?> =
        tickerRemoteDataSource.getTransactionInfo(ticker, count)

    fun getOrderBookInfo(ticker: String, count: Int): Flow<OrderData> =
        tickerRemoteDataSource.getOrderBookInfo(ticker, count)
}