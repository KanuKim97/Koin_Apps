package com.example.koin_apps.data.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CoinDao {
    @Query("SELECT * FROM cointitle")
    fun getAll(): List<CoinTitle>

    /*
    @Insert
    fun insertAll()

    @Delete
    fun delete(coinTitle: CoinTitle)
    */
}