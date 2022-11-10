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
            "DB_v01"
        ).build()
    }

    fun provideDao(AppDB: AppDataBase): CoinDao {
        return AppDB.coinTitleDao()
    }

    fun provideUserRepo(coinDao: CoinDao): AppRepository {
        return AppRepository(coinDao)
    }

}