package com.example.koin_apps.di.modules

import android.content.Context
import androidx.room.Room
import com.example.data.database.TickerDataBase
import com.example.data.database.dao.TickerDao
import com.example.data.remote.BithumbApiService
import com.example.data.repositoryImpl.BithumbApiRepositoryImpl
import com.example.data.repositoryImpl.DataBaseRepositoryImpl
import com.example.domain.repository.BithumbApiRepository
import com.example.domain.repository.DataBaseRepository
import com.example.koin_apps.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun providesBaseUrl(): String = BuildConfig.BITHUMB_PUBLIC_URL

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
    fun providesApiService(retrofit: Retrofit): BithumbApiService =
        retrofit.create(BithumbApiService::class.java)

    @Provides
    @Singleton
    fun createCoinTitleDBInstance(@ApplicationContext context: Context): TickerDataBase =
        Room.databaseBuilder(context, TickerDataBase::class.java, "ticker_DataBase")
            .build()

    @Provides
    @Singleton
    fun providesCoinDAOService(tickerDB: TickerDataBase): TickerDao = tickerDB.tickerDao()

    @Provides
    @Singleton
    fun providesDBRepositoryImpl(tickerDao: TickerDao): DataBaseRepository =
        DataBaseRepositoryImpl(tickerDao)

    @Provides
    @Singleton
    fun provideApiRepository(bithumbApiService: BithumbApiService): BithumbApiRepository =
        BithumbApiRepositoryImpl(bithumbApiService)
}