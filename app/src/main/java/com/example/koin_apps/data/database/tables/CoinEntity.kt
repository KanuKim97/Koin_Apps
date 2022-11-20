package com.example.koin_apps.data.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_table")
data class CoinEntity(
    @PrimaryKey(autoGenerate = true)
    var uid: Int,
    @ColumnInfo(name = "coinTitle")
    var coinTitle: String
)