package com.example.koin_apps.module

import android.content.Context
import android.widget.Toast
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** Toast Message Module **/
@Module
@InstallIn(SingletonComponent::class)
object ToastModule {
    @Provides
    @Singleton
    fun providesToast(@ApplicationContext context: Context): Toast =
        Toast.makeText(context, "", Toast.LENGTH_SHORT)
}