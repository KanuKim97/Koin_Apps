package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.tables.TickerTables

@Dao
interface TickerDao {
    @Query("SELECT * FROM Ticker_Table ORDER BY Ticker ASC")
    fun readAllTicker(): List<TickerTables>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicker(ticker: TickerTables)

    @Delete
    suspend fun deleteTicker(ticker: TickerTables)
}