package com.example.data.remote

import com.example.data.remote.model.orderbook.OrderRoot
import com.example.data.remote.model.ticker.TickerAllRoot
import com.example.data.remote.model.ticker.TickerRoot
import com.example.data.remote.model.transaction.TransactionRoot
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BithumbApiService {
    @GET("ticker/ALL_KRW")
    suspend fun getTickerALL(): Response<TickerAllRoot>

    @GET("ticker/{path}_KRW")
    suspend fun getTickerInfo(@Path("path") path: String): Response<TickerRoot>

    @GET("transaction_history/{path}_KRW")
    suspend fun getTransactionHistory(
        @Path("path") path: String,
        @Query("count") count: Int
    ): Response<TransactionRoot>

    @GET("orderbook/{path}_KRW")
    suspend fun getOrderBook(
        @Path("path") path: String,
        @Query("count") count: Int
    ): Response<OrderRoot>
}