package com.example.koin_apps.data.di

import android.content.Context
import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.database.AppDataBase
import com.example.koin_apps.data.database.dao.CoinDao
import com.example.koin_apps.data.remote.IKoinApiService
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import androidx.room.Room
import retrofit2.converter.gson.GsonConverterFactory
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.Provides
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /* Create Singleton Room DB Instance  */
    @Provides
    @Singleton
    fun createRoomDBInstance(@ApplicationContext context: Context): AppDataBase =
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "coinDB_ver0.1"
        ).allowMainThreadQueries() // allow using DB Queries in Main Thread
            .build()

    @Provides
    @Singleton
    fun provideDaoService(AppDB: AppDataBase): CoinDao = AppDB.coinTitleDao()

    /* Create Singleton Retrofit Instance */
    @Provides
    fun provideBaseUrl() = Constants.IKoinPublicApiUri

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun getClient(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(provideBaseUrl())
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): IKoinApiService =
        retrofit.create(IKoinApiService::class.java)

    /* provide AppRepository Room, Retrofit */
    @Provides
    @Singleton
    fun provideAppRepos(coinDao: CoinDao, koinApiService: IKoinApiService): AppRepository =
        AppRepository(coinDao, koinApiService)
}