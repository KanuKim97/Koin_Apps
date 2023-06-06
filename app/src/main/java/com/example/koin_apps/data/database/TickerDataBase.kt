package com.example.koin_apps.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.koin_apps.data.database.tables.TickerEntity
import com.example.koin_apps.data.database.dao.TickerDao

@Database(entities = [TickerEntity::class], version = 1, exportSchema = false)
abstract class TickerDataBase: RoomDatabase() {
    abstract fun tickerDao(): TickerDao
}