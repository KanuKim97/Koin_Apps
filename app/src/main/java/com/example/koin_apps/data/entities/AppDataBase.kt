package com.example.koin_apps.data.entities

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.koin_apps.data.entities.db.CoinTitle
import com.example.koin_apps.data.entities.remote.CoinDao

@Database(entities = [CoinTitle::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun coinTitleDao(): CoinDao
}