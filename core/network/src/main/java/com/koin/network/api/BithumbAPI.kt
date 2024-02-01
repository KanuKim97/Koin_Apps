package com.koin.network.api

import com.koin.model.orderbook.OrderRoot
import com.koin.model.ticker.TickerAllRoot
import com.koin.model.ticker.TickerRoot
import com.koin.model.transaction.TransactionRoot
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BithumbAPI {

    @GET("ticker/ALL_{currency}")
    suspend fun getTickerAll(@Path("currency") currency: String): Response<TickerAllRoot>

    @GET("ticker/{ticker}_{currency}")
    suspend fun getTickerInfo(
        @Path("ticker") ticker: String,
        @Path("currency") currency: String
    ): Response<TickerRoot>

    @GET("transaction_history/{ticker}_{currency}")
    suspend fun getTransactionHistory(
        @Path("ticker") ticker: String,
        @Path("currency") currency: String,
        @Query("count") count: Int
    ): Response<TransactionRoot>

    @GET("orderbook/{ticker}_{currency}")
    suspend fun getOrderBook(
        @Path("ticker") ticker: String,
        @Path("currency") currency: String,
        @Query("count") count: Int
    ): Response<OrderRoot>

}