package com.example.koin_apps.data.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "Ticker_Table")
data class TickerEntity(
    @PrimaryKey
    @ColumnInfo(name= "Ticker")
    var Ticker: String
)