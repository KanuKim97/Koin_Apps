package com.example.koin_apps.data.di.module

import android.content.Context
import androidx.room.Room
import com.example.koin_apps.data.database.CoinDataBase
import com.example.koin_apps.data.database.dao.CoinDao
import com.example.koin_apps.data.di.repository.CoinTitleDBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/* Room Database Module */
@Module
@InstallIn(SingletonComponent::class)
object CoinTitleDBModule {
    @Provides
    @Singleton
    fun createCoinTitleDBInstance(@ApplicationContext context: Context): CoinDataBase =
        Room.databaseBuilder(context, CoinDataBase::class.java, "cointitle_database")
            .build()

    @Provides
    @Singleton
    fun providesCoinDAOService(CoinDB: CoinDataBase): CoinDao = CoinDB.coinTitleDao()

    @Provides
    @Singleton
    fun providesCoinDBRepository(coinDao: CoinDao) = CoinTitleDBRepository(coinDao)
}