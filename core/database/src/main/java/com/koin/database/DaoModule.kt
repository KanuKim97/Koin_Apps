package com.koin.database

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideDao(
        koinDataBase: KoinDataBase
    ) = koinDataBase.koinDao()
}