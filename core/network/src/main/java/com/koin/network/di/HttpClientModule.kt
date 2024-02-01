package com.koin.network.di

import com.koin.network.constant.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object HttpClientModule {
    @Provides
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
            .callTimeout(Constants.TIME_OUT, TimeUnit.SECONDS)
            .build()
}