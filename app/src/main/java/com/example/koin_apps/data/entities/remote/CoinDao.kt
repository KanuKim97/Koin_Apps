package com.example.koin_apps.data.entities.remote

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.koin_apps.data.entities.db.CoinTitle

@Dao
interface CoinDao {
    @Query("SELECT * FROM cointitle")
    fun getAll(): List<CoinTitle>

    @Insert
    fun insertSelected(selectedCoin: CoinTitle)

}