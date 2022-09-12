package com.example.koin_apps.data.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface KoinDAOService {
    //TODO need to write Query
    @Query("select * from koin_list")
    fun getChecked(): List<KoinDAO>

    @Insert(onConflict = REPLACE)
    fun insertCoin(coinTitle: KoinDAO)

    @Delete
    fun deleteCoin(coinTitle: KoinDAO)
}