package com.koin.data.di

import com.koin.data.repository.BithumbAPIRepository
import com.koin.data.repository.DatabaseRepository
import com.koin.data.repositoryImpl.BithumbAPIRepositoryImpl
import com.koin.data.repositoryImpl.DataBaseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsBithumbRepository(impl: BithumbAPIRepositoryImpl): BithumbAPIRepository

    @Binds
    fun bindsDataBaseRepository(impl: DataBaseRepositoryImpl): DatabaseRepository
}