package com.example.koin_apps.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.example.koin_apps.common.Constants.IKoinPublicApiUri

object RetrofitClient {

    val koinApiService_Public: IKoinApiService
        get() = getClient(IKoinPublicApiUri)
            .create(IKoinApiService::class.java)

    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String) : Retrofit {
        if(retrofit == null) {

            val httpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!

    }

}