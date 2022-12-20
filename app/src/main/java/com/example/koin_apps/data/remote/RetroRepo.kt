package com.example.koin_apps.data.remote

import com.example.koin_apps.common.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetroRepo {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if(retrofit == null) {
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(Constants.IKoinPublicApiUri)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
        return retrofit!!
    }

}