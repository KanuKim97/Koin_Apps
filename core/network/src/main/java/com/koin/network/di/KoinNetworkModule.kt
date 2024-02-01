package com.koin.network.di

import com.koin.network.constant.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object KoinNetworkModule {
    @Provides
    fun provideKoinNetwork(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BITHUMB_PUBLIC_URI)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}