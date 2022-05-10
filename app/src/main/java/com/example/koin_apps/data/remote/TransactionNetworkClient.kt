package com.example.koin_apps.data.remote

import com.example.koin_apps.BuildConfig
import com.example.koin_apps.common.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object TransactionNetworkClient {

    private fun createLiveTransaction(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        if(BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            interceptor.level = HttpLoggingInterceptor.Level.NONE


        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .build()
    }

    private val koinTransactionClient = Retrofit.Builder()
        .baseUrl(Constants.IKoinApiUri).addConverterFactory(
            GsonConverterFactory.create())
        .client(createLiveTransaction())
        .build()

    val koinNetwork: IKoinApiService = koinTransactionClient.create(IKoinApiService::class.java)

}