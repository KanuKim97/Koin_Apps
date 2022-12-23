package com.example.koin_apps.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.koin_apps.data.database.tables.CoinEntity

@Dao
interface CoinDao {
    @Query("SELECT * FROM coin_table ORDER BY coinTitle ASC")
    fun readAllData(): LiveData<List<CoinEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinTitle(selectedCoin: CoinEntity)

    @Delete
    fun deleteCoinTitle(selectedCoin: CoinEntity)
}