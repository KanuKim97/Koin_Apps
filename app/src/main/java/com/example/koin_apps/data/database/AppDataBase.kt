package com.example.koin_apps.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.database.dao.CoinDao

@Database(entities = [CoinEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun coinTitleDao(): CoinDao
}