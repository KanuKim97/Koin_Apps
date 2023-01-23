package com.example.koin_apps.data.database

import androidx.room.Room
import com.example.koin_apps.AndroidApp
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.dao.CoinDao

object RoomRepo{
    //Create Room DB Client
    fun createAppDBClient(): AppDataBase {
        return Room.databaseBuilder(
            AndroidApp.getApplicationContext(),
            AppDataBase::class.java,
            "coinDB_ver0.1"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideDao(AppDB: AppDataBase): CoinDao {
        return AppDB.coinTitleDao()
    }

}