package com.example.koin_apps.data.entities

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.koin_apps.data.entities.db.CoinEntity
import com.example.koin_apps.data.entities.remote.CoinDao

@Database(entities = [CoinEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun coinTitleDao(): CoinDao
}