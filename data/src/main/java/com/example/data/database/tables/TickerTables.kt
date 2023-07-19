package com.example.data.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "Ticker_Table")
data class TickerTables(@PrimaryKey @ColumnInfo(name= "Ticker") var Ticker: String)