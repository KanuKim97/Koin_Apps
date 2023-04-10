package com.example.koin_apps.data.di.module

import android.content.Context
import androidx.room.Room
import com.example.koin_apps.data.database.TickerDataBase
import com.example.koin_apps.data.database.dao.TickerDao
import com.example.koin_apps.data.di.dataSource.TickerDBDataSource
import com.example.koin_apps.data.di.repository.TickerDBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** Room Database Module **/
@Module
@InstallIn(SingletonComponent::class)
object TickerDBModule {
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
    fun providesCoinDBRepository(tickerDAO: TickerDao) = TickerDBDataSource(tickerDAO)
}