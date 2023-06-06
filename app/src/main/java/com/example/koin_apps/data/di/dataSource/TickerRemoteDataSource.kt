package com.example.koin_apps.data.di.dataSource

import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.model.orderBook.OrderData
import com.example.koin_apps.data.remote.model.orderBook.OrderRoot
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.data.remote.model.transaction.TransactionData
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.retryWhen
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

/**
 * Bithumb Public API Service (Coroutine Flow Producer)
 *  1. getTickerInfoAll() : Get All CryptoCurrency Ticker Code
 *  2. getTickerInfo() : Get Ticker's information (ex. openingPrice, closingPrice...)
 *  3. getTransactionInfo() : Get CryptoCurrency Transactions
 *  4. getOrderBookInfo() : Get CryptoCurrency Order information
 * */
class TickerRemoteDataSource @Inject constructor(
    private val koinApiService: IKoinApiService
) {
    fun getTickerInfoAll(): Flow<MutableList<String?>> = flow {
        val response: Response<TickerRoot> = koinApiService.getTickerALL()

        if (response.isSuccessful && response.body()?.data != null) {
            val result: MutableList<String?> = response.body()?.data?.keys!!.toMutableList()
            emit(result)
        } else {
            if (response.body()?.data == null) {
                throw (NullPointerException("response body data is Null"))
            }
            if (!response.isSuccessful) {
                throw (HttpException(response))
            }
        }
    }.catch { exception ->
        if (exception is IOException) {
            emit(mutableListOf())
        } else { throw exception }
    }.retryWhen { cause, attempt ->
        when {
            (cause is HttpException && attempt < Constants.RETRY_MAX_ATTEMPT) -> {
                delay(Constants.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

    fun getTickerInfo(ticker: String): Flow<Map<String?, Any?>?> = flow {
        val response: Response<TickerRoot> = koinApiService.getTicker(ticker)

        if (response.isSuccessful && response.body()?.data != null) {
            val result: Map<String?, Any?>? = response.body()?.data
            emit(result)
        } else {
            if (response.body()?.data == null) {
                throw (NullPointerException("response body data is Null"))
            }
            if (!response.isSuccessful) {
                throw (HttpException(response))
            }
        }
    }.catch { exception ->
        if (exception is IOException) {
            TODO("emit() what?")
        } else { throw exception }
    }.retryWhen { cause, attempt ->
        when {
            (cause is HttpException && attempt < Constants.RETRY_MAX_ATTEMPT) -> {
                delay(Constants.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

    fun getTransactionInfo(path: String, count: Int): Flow<ArrayList<TransactionData>?> = flow {
        val response: Response<TransactionRoot> = koinApiService.getTransactionHistory(path, count)

        if (response.isSuccessful && response.body()?.data != null) {
            val result: ArrayList<TransactionData>? = response.body()?.data
            emit(result)
        } else {
            if (response.body()?.data == null) {
                throw (NullPointerException("response body data is Null"))
            }
            if (!response.isSuccessful) {
                throw (HttpException(response))
            }
        }
    }.catch { exception ->
        if (exception is IOException) {
            emit(ArrayList())
        } else { throw exception }
    }.retryWhen { cause, attempt ->
        when {
            (cause is HttpException && attempt < Constants.RETRY_MAX_ATTEMPT) -> {
                delay(Constants.DELAY_TIME_MILLIS)
                true
            }
            else -> false
        }
    }

    fun getOrderBookInfo(path: String, count: Int): Flow<OrderData> = flow {
        val response: Response<OrderRoot> = koinApiService.getOrderBook(path, count)

        if (response.isSuccessful && response.body()?.data != null) {
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
    }.catch { exception ->
        if (exception is IOException) {
            emit(
                OrderData(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
        } else { throw exception }
    }.retryWhen { cause, attempt ->
        when {
            (cause is HttpException && attempt < Constants.RETRY_MAX_ATTEMPT) -> {
                delay(Constants.DELAY_TIME_MILLIS)
                true
            }
            else ->  false
        }
    }
}