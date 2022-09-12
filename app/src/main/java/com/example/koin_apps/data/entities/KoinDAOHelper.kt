package com.example.koin_apps.data.entities

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [KoinDAO::class], version = 1, exportSchema = false)
abstract class KoinDAOHelper: RoomDatabase() {
    abstract fun roomKoinDao(): KoinDAO
}