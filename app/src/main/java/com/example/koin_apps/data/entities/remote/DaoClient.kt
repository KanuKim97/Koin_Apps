package com.example.koin_apps.data.entities.remote

import androidx.room.Room
import com.example.koin_apps.AndroidApp
import com.example.koin_apps.data.entities.AppDataBase

object DaoClient {

    fun createDBClient(): AppDataBase {
        return Room.databaseBuilder(
            AndroidApp.getApplicationContext(),
            AppDataBase::class.java,
            "DB_v01"
        ).build()
    }
}