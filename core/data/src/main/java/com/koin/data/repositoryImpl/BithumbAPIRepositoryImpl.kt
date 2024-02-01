package com.koin.data.repositoryImpl

import com.koin.common.CoreDispatcher
import com.koin.common.Dispatcher
import com.koin.data.repository.BithumbAPIRepository
import com.koin.model.orderbook.OrderRoot
import com.koin.model.ticker.TickerAllRoot
import com.koin.model.ticker.TickerRoot
import com.koin.model.transaction.TransactionRoot
import com.koin.network.KoinNetwork
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BithumbAPIRepositoryImpl @Inject constructor(
    private val network: KoinNetwork,
    @CoreDispatcher(Dispatcher.IO) private val ioDispatcher: CoroutineDispatcher
): BithumbAPIRepository {
    override fun getTickerAll(currency: String): Flow<TickerAllRoot> = flow {
        val response = network.getTickerAll(currency)
        emit(response)
    }.flowOn(ioDispatcher)

    override fun getTickerInfo(
        ticker: String,
        currency: String
    ): Flow<TickerRoot> = flow {
        val response = network.getTickerInfo(ticker, currency)
        emit(response)
    }.flowOn(ioDispatcher)

    override fun getTransactionHistory(
        ticker: String,
        currency: String,
        count: Int,
    ): Flow<TransactionRoot> = flow {
        val response = network.getTransactionHistory(
            ticker = ticker,
            currency = currency,
            count = count
        )

        emit(response)
    }.flowOn(ioDispatcher)

    override fun getOrderBook(
        ticker: String,
        currency: String,
        count: Int
    ): Flow<OrderRoot> = flow {
        val response = network.getOrderBook(
            ticker = ticker,
            currency = currency,
            count = count
        )

        emit(response)
    }.flowOn(ioDispatcher)
}