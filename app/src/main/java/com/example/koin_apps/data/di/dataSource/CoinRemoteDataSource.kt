package com.example.koin_apps.data.di.dataSource

import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.model.orderBook.OrderData
import com.example.koin_apps.data.remote.model.orderBook.OrderRoot
import com.example.koin_apps.data.remote.model.ticker.LiveTickerData
import com.example.koin_apps.data.remote.model.ticker.OrderTickerData
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.data.remote.model.transaction.TransactionData
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.retryWhen
import retrofit2.Response
import javax.inject.Inject

/* Coroutine Flow Producer -> Bithumb Public API Service */
class CoinRemoteDataSource @Inject constructor(
    private val koinApiService: IKoinApiService
) {

    fun getTickerInfoAll(): Flow<MutableList<String?>> = flow {
        try {
            val response: Response<TickerRoot> = koinApiService.getTickerALL()

            if (response.isSuccessful && response.body()?.data != null) {
                val result: MutableList<String?> = response.body()?.data?.keys!!.toMutableList()
                emit(result)
            } else {
                if (response.body()?.data == null) {
                    throw (Exception("response body data is Null"))
                }
                if (!response.isSuccessful) {
                    throw(Exception("${response.code()} + ${response.body()?.message}"))
                }
            }
        } catch (e: Exception) { e.printStackTrace() }
    }.retryWhen { cause, _ ->
        cause is Exception
    }.catch {
        it.printStackTrace()
    }

    fun getTickerInfo(ticker: String): Flow<Map<String?, Any?>?> = flow {
        try {
            val response: Response<TickerRoot> = koinApiService.getTicker(ticker)

            if (response.isSuccessful && response.body()?.data != null) {
                val result: Map<String?, Any?>? = response.body()?.data
                emit(result)
            } else {
                if (response.body()?.data == null) {
                    throw (Exception("response body data is Null"))
                }
                if (!response.isSuccessful) {
                    throw(Exception("${response.code()} + ${response.body()?.message}"))
                }
            }
        } catch (e: Exception) { e.printStackTrace() }
    }.retryWhen { cause, _ ->
        cause is Exception
    }.catch {
        it.printStackTrace()
    }

    fun getTransactionInfo(path: String, count: Int): Flow<ArrayList<TransactionData>?> = flow {
        try {
            val response: Response<TransactionRoot> = koinApiService.getTransactionHistory(path, count)

            if (response.isSuccessful && response.body()?.data != null) {
                val result: ArrayList<TransactionData>? = response.body()?.data
                emit(result)
            } else {
                if (response.body()?.data == null) {
                    throw (Exception("response body data is Null"))
                }
                if (!response.isSuccessful) {
                    throw (Exception("${response.code()} + ${response.body()?.message}"))
                }
            }
        } catch (e: Exception) { e.printStackTrace() }
    }.retryWhen { cause, _ ->
        cause is Exception
    }.catch {
        it.printStackTrace()
    }

    fun getOrderBookInfo(path: String, count: Int): Flow<OrderData> = flow {
        try {
            val response: Response<OrderRoot> = koinApiService.getOrderBook(path, count)

            if(response.isSuccessful && response.body()?.data != null) {
                val result: OrderData = response.body()?.data!!
                emit(result)
            } else {
                if (response.body()?.data == null) {
                    throw (Exception("response body data is Null"))
                }
                if (!response.isSuccessful) {
                    throw (Exception("${response.code()} + ${response.body()?.message}"))
                }
            }
        } catch (e: Exception) { e.printStackTrace() }
    }.retryWhen { cause, _ ->
        cause is Exception
    }.catch {
        it.printStackTrace()
    }

}