package com.example.koin_apps.data.remote

import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface IKoinApiService {

    @GET
    fun getKoinPrice(
        @Url url: String
    ): Call<TickerRoot>

    @GET
    fun getKoinTransaction(
        @Url url: String
    ): Call<TransactionRoot>

    //TEST CODE
    @GET("ticker/{path}_KRW")
    fun getTicker(
        @Path("path")
        path: String
    ): Call<TickerRoot>

    @GET("transaction_history/{path}_KRW")
    fun getTransactionHistory(
        @Path("path")
        path: String,
        @Query("count")
        count: Int
    ): Call<TransactionRoot>
}