package com.example.koin_apps.data.di.module

import android.content.Context
import androidx.appcompat.app.AlertDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** AlertDialog Module **/
@Module
@InstallIn(SingletonComponent::class)
object AlertDialogModule {
    @Provides
    @Singleton
    fun providesAlertDialog(@ApplicationContext context: Context): AlertDialog.Builder =
        AlertDialog.Builder(context)
}