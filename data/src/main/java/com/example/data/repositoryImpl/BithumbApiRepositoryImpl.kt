package com.example.data.repositoryImpl

import com.example.data.remote.BithumbApiService
import com.example.data.remote.model.ticker.TickerAllRoot
import com.example.data.remote.model.transaction.TransactionRoot
import com.example.data.util.Constants
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
    override fun getTickerInfoAll(): Flow<MutableList<String>?> = flow {
        while (true) {
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

            delay(Constants.FLOW_MAX_DELAY_TIME)
        }
    }.retryWhen { cause: Throwable, attempt: Long ->
        when {
            (cause is IOException && attempt < Constants.FLOW_MAX_RETRY_ATTEMPT) -> {
                delay(Constants.FLOW_MAX_DELAY_TIME)
                true
            }
            (cause is HttpException && attempt < Constants.FLOW_MAX_RETRY_ATTEMPT) -> {
                delay(Constants.FLOW_MAX_DELAY_TIME)
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

    override fun getTickerInfo(ticker: String): Flow<Map<String, Any>?> = flow<Map<String, Any>?> {

    }.retryWhen { cause: Throwable, attempt: Long ->
        when {
            (cause is IOException && attempt < Constants.FLOW_MAX_RETRY_ATTEMPT) -> {
                delay(Constants.FLOW_MAX_DELAY_TIME)
                true
            }
            (cause is HttpException && attempt < Constants.FLOW_MAX_RETRY_ATTEMPT) -> {
                delay(Constants.FLOW_MAX_DELAY_TIME)
                true
            }
            else -> false
        }
    }.catch { cause ->
        when (cause) {
            is IOException -> emit(mapOf())
            is HttpException -> emit(mapOf())
            is NullPointerException -> emit(mapOf())
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

                emit(listOf())
            } else {
                when {
                    (!response.isSuccessful) -> throw HttpException(response)
                    (response.body()?.transactionData == null) -> throw NullPointerException()
                }
            }
        }
    }.retryWhen { cause: Throwable, attempt: Long ->
        when {
            (cause is IOException && attempt < Constants.FLOW_MAX_RETRY_ATTEMPT) -> {
                delay(Constants.FLOW_MAX_DELAY_TIME)
                true
            }
            (cause is HttpException && attempt < Constants.FLOW_MAX_RETRY_ATTEMPT) -> {
                delay(Constants.FLOW_MAX_DELAY_TIME)
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

    }.retryWhen { cause: Throwable, attempt: Long ->
        when {
            (cause is IOException && attempt < Constants.FLOW_MAX_RETRY_ATTEMPT) -> {
                delay(Constants.FLOW_MAX_DELAY_TIME)
                true
            }
            (cause is HttpException && attempt < Constants.FLOW_MAX_RETRY_ATTEMPT) -> {
                delay(Constants.FLOW_MAX_DELAY_TIME)
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
}