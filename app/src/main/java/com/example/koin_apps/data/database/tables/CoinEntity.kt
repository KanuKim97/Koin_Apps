package com.example.koin_apps.data.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName= "coin_table")
data class CoinEntity(
    @PrimaryKey
    @ColumnInfo(name= "coinTitle")
    var coinTitle: String
)