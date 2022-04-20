package com.example.koin_apps.data.remote

import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface IKoinApiService {

    @GET
    fun getKoinPrice(@Url url: String): Call<TickerRoot>

    @GET
    fun getKoinTransaction(@Url url: String): Call<TransactionRoot>

}