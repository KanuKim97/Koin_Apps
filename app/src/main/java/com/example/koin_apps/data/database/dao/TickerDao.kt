package com.example.koin_apps.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.koin_apps.data.database.tables.TickerEntity

@Dao
interface TickerDao {
    @Query("SELECT * FROM Ticker_Table ORDER BY Ticker ASC")
    fun readAllTicker(): LiveData<List<TickerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicker(ticker: TickerEntity)

    @Delete
    suspend fun deleteTicker(ticker: TickerEntity)
}