package com.example.koin_apps.data.di.repository

import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.model.orderBook.OrderRoot
import com.example.koin_apps.data.remote.model.ticker.LiveTickerData
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

/* Bithumb public Api Repository */
class ApiRepository @Inject constructor(
    private val koinApiService: IKoinApiService
) {
    suspend fun getTickerAll(): Response<TickerRoot> = koinApiService.getTickerALL()
    suspend fun getTicker(path: String): Response<TickerRoot> = koinApiService.getTicker(path)
    suspend fun getTransactionHistory(path: String, count: Int): Response<TransactionRoot> =
        koinApiService.getTransactionHistory(path, count)
    suspend fun getOrderBook(path: String, count: Int): Response<OrderRoot> =
        koinApiService.getOrderBook(path, count)

    fun getTickerAllFlow(): Flow<List<String?>> = flow {
        try {
            val response: Response<TickerRoot> = koinApiService.getTickerALL()
            val tickerTitle: List<String?> = response.body()?.data?.keys!!.toList()
            if (response.isSuccessful) { emit(tickerTitle) }
        } catch (e: Exception) { e.printStackTrace() }
    }

    fun getTickerInfo(ticker: String): Flow<LiveTickerData> = flow {
        try {
            val tickerInfo: Response<TickerRoot> = koinApiService.getTicker(ticker)

            if (tickerInfo.isSuccessful && tickerInfo.body() != null) {
                emit(
                    LiveTickerData(
                        tickerInfo.body()?.data?.get("closing_price").toString(),
                        tickerInfo.body()?.data?.get("fluctate_24H").toString(),
                        tickerInfo.body()?.data?.get("fluctate_rate_24H").toString()
                    )
                )
            }
        } catch (e: Exception) { e.printStackTrace() }
    }

}