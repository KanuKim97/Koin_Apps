package com.koin.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.koin.database.model.KoinTable

@Dao
interface KoinDao {
    @Query("SELECT * FROM Ticker_Table ORDER BY Ticker ASC")
    fun readAllTicker(): List<KoinTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicker(ticker: KoinTable)

    @Delete
    suspend fun deleteTicker(ticker: KoinTable)
}