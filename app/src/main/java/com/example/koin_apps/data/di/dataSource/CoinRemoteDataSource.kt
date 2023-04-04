package com.example.koin_apps.data.di.dataSource

import android.util.Log
import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.model.orderBook.OrderData
import com.example.koin_apps.data.remote.model.orderBook.OrderRoot
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.data.remote.model.transaction.TransactionData
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.retryWhen
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
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
                    throw (Exception("${response.code()} + ${response.body()?.message}"))
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
                    throw (Exception("${response.code()} + ${response.body()?.message}"))
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
                    throw (Exception ("${response.code()} + ${response.body()?.message}"))
                }
            }
        } catch (e: Exception) { e.printStackTrace() }
    }.retryWhen { cause, _ ->
        cause is Exception
    }.catch {
        it.printStackTrace()
    }

    fun getOrderBookInfo(path: String, count: Int): Flow<OrderData> = flow {
        val response: Response<OrderRoot> = koinApiService.getOrderBook(path, count)

        if(response.isSuccessful && response.body()?.data != null) {
            val result: OrderData = response.body()?.data!!
            emit(result)
        } else {
            if (response.body()?.data == null) {
                throw (NullPointerException("response body data is Null"))
            }
            if (!response.isSuccessful) {
                throw (HttpException(response))
            }
        }
    }.retryWhen { cause, _ ->
        when (cause) {
            is IOException -> {
                // TODO (IO Exception Handling)
                false
            }
            is HttpException -> {
                // TODO (Http Exception Handling)
                false
            }
            else -> {
                // TODO (Http Exception Handling)
                false
            }
        }
    }.catch { cause ->
        when (cause) {
            is IOException -> {
                Log.e(Constants.LogErrorTAG, "IOException: ${cause.message}")
            }
            is HttpException -> {
                Log.e(Constants.LogErrorTAG, "HttpException: ${cause.message}")
            }
            else -> {
                Log.e(Constants.LogErrorTAG, "Exception: ${cause.message}")
            }
        }
    }

}