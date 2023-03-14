package com.example.koin_apps.data.di.module

import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.di.repository.ApiRepository
import com.example.koin_apps.data.remote.IKoinApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/* Bithumb Public Api Module */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    fun providesBaseUrl(): String = Constants.IKoinPublicApiUri

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun getRetrofitClient(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(providesBaseUrl())
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): IKoinApiService =
        retrofit.create(IKoinApiService::class.java)

    @Provides
    @Singleton
    fun providesApiRepository(koinApiService: IKoinApiService): ApiRepository =
        ApiRepository(koinApiService)
}