package com.example.koin_apps.data.entities

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CoinTitle::class], version = 1)
abstract class CoinTitleDB: RoomDatabase() {
    abstract fun coinTitleDao(): CoinDao
}