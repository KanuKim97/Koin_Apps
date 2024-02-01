package com.koin.network.di

import com.koin.network.KoinDataSource
import com.koin.network.KoinNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {
    @Binds
    fun bindKoinDataSource(impl: KoinNetwork): KoinDataSource
}