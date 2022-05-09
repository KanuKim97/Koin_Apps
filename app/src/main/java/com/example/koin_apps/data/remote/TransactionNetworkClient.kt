package com.example.koin_apps.data.remote

import com.example.koin_apps.common.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object TransactionNetworkClient {

    private fun createLiveTransaction(): OkHttpClient {

        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    private val koinTransactionClient = Retrofit.Builder()
        .baseUrl(Constants.IKoinApiUri).addConverterFactory(
            GsonConverterFactory.create())
        .client(createLiveTransaction())

}