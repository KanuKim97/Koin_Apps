package com.example.koin_apps.data.entities.remote

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.koin_apps.data.entities.db.CoinEntity

@Dao
interface CoinDao {
    @Query("SELECT * FROM coin_table ORDER BY uid ASC")
    fun readAllData(): LiveData<List<CoinEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSelected(selectedCoin: CoinEntity)
}