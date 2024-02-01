package com.koin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.koin.database.dao.KoinDao
import com.koin.database.model.KoinTable

@Database(
    entities = [KoinTable::class],
    version = 1,
    exportSchema = false
)
abstract class KoinDataBase: RoomDatabase() {
    abstract fun koinDao(): KoinDao
}