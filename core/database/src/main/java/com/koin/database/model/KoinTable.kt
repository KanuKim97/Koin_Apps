package com.koin.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "Ticker_Table")
data class KoinTable(@PrimaryKey @ColumnInfo(name= "Ticker") var Ticker: String)