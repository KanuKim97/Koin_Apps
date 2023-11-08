package com.example.data.repositoryImpl

import com.example.data.remote.BithumbApiService
import com.example.data.remote.model.orderbook.OrderRoot
import com.example.data.remote.model.ticker.TickerAllRoot
import com.example.data.remote.model.ticker.TickerRoot
import com.example.data.remote.model.transaction.TransactionRoot
import com.example.data.util.toOrderBookEntityMapper
import com.example.data.util.toTickerDataEntityMapper
import com.example.data.util.toTransactionEntityMapper
import com.example.domain.entity.api.orderBook.OrderBookEntity
import com.example.domain.entity.api.ticker.TickerInfoDetailEntity
import com.example.domain.entity.api.transaction.TransactionEntity
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
    /* override fun getTickerInfoAll(): Flow<MutableList<String>?> = flow {
        val response: Response<TickerAllRoot> = bithumbApiService.getTickerALL()

        if (response.isSuccessful && response.body()?.tickerData != null) {
            val tickerTitleList = response.body()?.tickerData?.keys?.toMutableList()
            emit(tickerTitleList)
        } else {
            when {
                (!response.isSuccessful) -> throw HttpException(response)
                (response.body()?.tickerData == null) -> throw NullPointerException()
            }
        }
    }.retryWhen { cause: Throwable, attempt: Long ->
        when {
            (cause is IOException && attempt < 3L) -> {
                delay(2000L)
                true
            }
            (cause is HttpException && attempt < 3L) -> {
                delay(2000L)
                true
            }
            else -> false
        }
    }.catch { cause ->
        when (cause) {
            is IOException -> emit(mutableListOf())
            is HttpException -> emit(mutableListOf())
            is NullPointerException -> emit(mutableListOf())
            else -> throw Exception()
        }
    }

    override fun getTickerInfo(ticker: String): Flow<TickerInfoDetailEntity?> = flow{
        while (true) {
            val response: Response<TickerRoot> = bithumbApiService.getTickerInfo(ticker)
            val responseBody: TickerRoot? = response.body()

            if (response.isSuccessful) {
                if (responseBody?.tickerData != null) {

                } else {

                }
            } else {
                when {
                    (!response.isSuccessful) -> throw HttpException(response)
                    (response.body()?.tickerData == null) -> throw NullPointerException()
                }
            }
        }
    }.retryWhen { cause: Throwable, attempt: Long ->
        when {
            (cause is IOException && attempt < 3L) -> {
                delay(2000L)
                true
            }
            (cause is HttpException && attempt < 3L) -> {
                delay(2000L)
                true
            }
            else -> false
        }
    }.catch { cause ->
        when (cause) {
            is IOException -> emit(null)
            is HttpException -> emit(null)
            is NullPointerException -> emit(null)
            else -> throw Exception()
        }
    }

    override fun getTransactionHistory(
        ticker: String,
        count: Int
    ): Flow<List<TransactionEntity>?> = flow<List<TransactionEntity>?> {
        while (true) {
            val response: Response<TransactionRoot> = bithumbApiService.getTransactionHistory(ticker, count)

            if (response.isSuccessful && response.body()?.transactionData != null) {
                val result = toTransactionEntityMapper(response.body()!!.transactionData)
                emit(result)
            } else {
                when {
                    (!response.isSuccessful) -> throw HttpException(response)
                    (response.body()?.transactionData == null) -> throw NullPointerException()
                }
            }
        }
    }.retryWhen { cause: Throwable, attempt: Long ->
        when {
            (cause is IOException && attempt < 3L) -> {
                delay(2000L)
                true
            }
            (cause is HttpException && attempt < 3L) -> {
                delay(2000L)
                true
            }
            else -> false
        }
    }.catch { cause ->
        when (cause) {
            is IOException -> emit(listOf())
            is HttpException -> emit(listOf())
            is NullPointerException -> emit(listOf())
            else -> throw Exception()
        }
    }

    override fun getOrderBookInfo(
        ticker: String,
        count: Int
    ): Flow<OrderBookEntity?> = flow<OrderBookEntity?> {
        while (true) {
            val response: Response<OrderRoot> = bithumbApiService.getOrderBook(ticker, count)

            if (response.isSuccessful && response.body() != null) {
                val result: OrderBookEntity = toOrderBookEntityMapper(response.body()!!)
                emit(result)
            } else {
                when {
                    (!response.isSuccessful) -> throw HttpException(response)
                    (response.body()?.orderData == null) -> throw NullPointerException()
                }
            }
        }
    }.retryWhen { cause: Throwable, attempt: Long ->
        when {
            (cause is IOException && attempt < 3L) -> {
                delay(2000L)
                true
            }
            (cause is HttpException && attempt < 3L) -> {
                delay(2000L)
                true
            }
            else -> false
        }
    }.catch { cause ->
        when (cause) {
            is IOException -> emit(null)
            is HttpException -> emit(null)
            is NullPointerException -> emit(null)
            else -> throw Exception()
        }
    }
    */
}