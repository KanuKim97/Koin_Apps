package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.TickerDao
import com.example.data.database.tables.TickerTables

@Database(entities = [TickerTables::class], version = 1, exportSchema = false)
abstract class TickerDataBase: RoomDatabase() {
    abstract fun tickerDao(): TickerDao
}