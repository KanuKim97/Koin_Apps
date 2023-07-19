package com.example.data.repositoryImpl

import com.example.data.remote.BithumbApiService
import com.example.data.remote.model.orderbook.OrderData
import com.example.data.remote.model.orderbook.OrderRoot
import com.example.data.remote.model.ticker.TickerRoot
import com.example.data.remote.model.transaction.TransactionData
import com.example.data.remote.model.transaction.TransactionRoot
import com.example.data.util.apiMapper.mapperToOrderBookEntity
import com.example.data.util.apiMapper.mapperToTransactionEntity
import com.example.domain.entity.OrderBookEntity
import com.example.domain.entity.TransactionEntity
import com.example.domain.repository.BithumbApiRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class BithumbApiRepositoryImpl @Inject constructor(
    private val bithumbApiService: BithumbApiService
): BithumbApiRepository {

    override fun getTickerInfoAll(): Flow<MutableList<String?>> = flow {
        val response: Response<TickerRoot> = bithumbApiService.getTickerALL()

        if (response.isSuccessful && response.body()?.data != null) {
            val responseResult: MutableList<String?> = response.body()?.data!!.keys.toMutableList()
            emit(responseResult)
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
        } else {
            throw exception
        }
    }.retryWhen { cause, attempt ->
        when {
            (cause is HttpException && attempt < 3) -> {
                delay(2000L)
                true
            }
            else -> false
        }
    }

    override fun getTickerInfo(ticker: String): Flow<Map<String?, Any?>> = flow {
        val response: Response<TickerRoot> = bithumbApiService.getTickerInfo(ticker)

        if (response.isSuccessful && response.body()?.data != null) {
            val responseResult: Map<String?, Any?> = response.body()!!.data
            emit(responseResult)
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
            emit(mapOf())
        } else {
            throw exception
        }
    }.retryWhen { cause, attempt ->
        when {
            (cause is HttpException && attempt < 3) -> {
                delay(2000L)
                true
            }
            else -> false
        }
    }

    override fun getTransactionHistory(
        ticker: String,
        count: Int
    ): Flow<List<TransactionEntity>> = flow {
        val response: Response<TransactionRoot> =
            bithumbApiService.getTransactionHistory(ticker, count)

        if (response.isSuccessful && response.body()?.data != null) {
            val responseResult: ArrayList<TransactionData> = response.body()?.data!!
            emit(mapperToTransactionEntity(responseResult))
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
            emit(listOf())
        } else {
            throw exception
        }
    }.retryWhen { cause, attempt ->
        when {
            (cause is HttpException && attempt < 3) -> {
                delay(2000L)
                true
            }
            else -> false
        }
    }

    override fun getOrderBookInfo(
        ticker: String,
        count: Int
    ): Flow<OrderBookEntity?> = flow {
        val response: Response<OrderRoot> = bithumbApiService.getOrderBook(ticker, count)

        if (response.isSuccessful && response.body()?.data != null) {
            val responseResult: OrderData = response.body()?.data!!
            emit(mapperToOrderBookEntity(responseResult))
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
                OrderBookEntity(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
        } else {
            throw exception
        }
    }.retryWhen { cause, attempt ->
        when {
            (cause is HttpException && attempt < 3) -> {
                delay(2000L)
                true
            }
            else ->  false
        }
    }

}