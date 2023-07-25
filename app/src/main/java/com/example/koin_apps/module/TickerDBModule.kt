package com.example.koin_apps.module

import android.content.Context
import androidx.room.Room
import com.example.data.database.TickerDataBase
import com.example.data.database.dao.TickerDao
import com.example.data.repositoryImpl.DataBaseRepositoryImpl
import com.example.domain.repository.DataBaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** Room ORM (Local Database) Module **/
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
    fun providesDBRepositoryImpl(tickerDao: TickerDao): DataBaseRepository =
        DataBaseRepositoryImpl(tickerDao)
}