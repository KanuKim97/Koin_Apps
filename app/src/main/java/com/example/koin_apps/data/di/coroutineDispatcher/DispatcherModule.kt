package com.example.koin_apps.data.di.coroutineDispatcher

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {
    @MainDispatcher
    @Provides
    fun providesMainDispatcher() = Dispatchers.Main

    @IoDispatcher
    @Provides
    fun providesIoDispatcher() = Dispatchers.IO

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher() = Dispatchers.Default
}