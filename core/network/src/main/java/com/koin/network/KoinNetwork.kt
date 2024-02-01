package com.koin.network

import com.koin.model.orderbook.OrderRoot
import com.koin.model.ticker.TickerAllRoot
import com.koin.model.ticker.TickerRoot
import com.koin.model.transaction.TransactionRoot
import com.koin.network.api.BithumbAPI
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KoinNetwork @Inject constructor(network: Retrofit): KoinDataSource {
    private val koinNetwork: BithumbAPI = network.create(BithumbAPI::class.java)

    override suspend fun getTickerAll(currency: String): TickerAllRoot {
        val response = koinNetwork.getTickerAll(currency)

        return if (response.isSuccessful) {
            response.body()!!
        } else {
            TickerAllRoot(
                status = response.code().toString(),
                message = response.message(),
                data = null
            )
        }
    }

    override suspend fun getTickerInfo(
        ticker: String,
        currency: String
    ): TickerRoot {
        val response = koinNetwork.getTickerInfo(ticker, currency)

        return if (response.isSuccessful) {
            response.body()!!
        } else {
            TickerRoot(
                status = response.code().toString(),
                message = response.message(),
                data = null
            )
        }
    }


    override suspend fun getTransactionHistory(
        ticker: String,
        currency: String,
        count: Int,
    ): TransactionRoot {
        val response = koinNetwork.getTransactionHistory(
            ticker = ticker,
            currency = currency,
            count = count
        )

        return if (response.isSuccessful) {
            response.body()!!
        } else {
            TransactionRoot(
                status = response.code().toString(),
                message = response.message(),
                data = arrayListOf()
            )
        }
    }

    override suspend fun getOrderBook(
        ticker: String,
        currency: String,
        count: Int
    ): OrderRoot {
        val response = koinNetwork.getOrderBook(
            ticker = ticker,
            currency = currency,
            count = count
        )

        return if (response.isSuccessful) {
            response.body()!!
        } else {
            OrderRoot(
                status = response.code().toString(),
                message = response.message(),
                data = null
            )
        }
    }


}