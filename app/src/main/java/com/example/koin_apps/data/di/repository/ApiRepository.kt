package com.example.koin_apps.data.di.repository

import com.example.koin_apps.data.di.coroutineDispatcher.IoDispatcher
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.model.orderBook.OrderRoot
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

/* Bithumb public Api Repository */
class ApiRepository @Inject constructor(
    private val koinApiService: IKoinApiService
) {
    fun getTickerTitleAll(): Flow<List<String?>> = flow {
        try {
            val response: Response<TickerRoot> = koinApiService.getTickerALL()
            val tickerTitle: List<String?> = response.body()?.data?.keys!!.toList()
            if (response.isSuccessful) { emit(tickerTitle) }
        } catch (e: Exception) { e.printStackTrace() }
    }

    fun getTickerInfo(ticker: String): Flow<TickerRoot> = flow {
        try {
            val tickerInfo: Response<TickerRoot> = koinApiService.getTicker(ticker)
            if (tickerInfo.isSuccessful && tickerInfo.body() != null) { emit(tickerInfo.body()!!) }
        } catch (e: Exception) { e.printStackTrace() }
    }

    fun getTransactionInfo(path: String, count: Int): Flow<TransactionRoot> = flow {
        try {
            val transactionInfo: Response<TransactionRoot> = koinApiService.getTransactionHistory(path, count)
            if (transactionInfo.isSuccessful && transactionInfo.body() != null) {
                emit(transactionInfo.body()!!)
            }
        } catch (e: Exception) { e.printStackTrace() }
    }

    fun getOrderBookInfo(path: String, count: Int): Flow<OrderRoot> = flow{
        try {
            val orderBookInfo: Response<OrderRoot> = koinApiService.getOrderBook(path, count)
            if (orderBookInfo.isSuccessful && orderBookInfo.body() != null) {
                emit(orderBookInfo.body()!!)
            }
        } catch (e: Exception) { e.printStackTrace() }
    }

}