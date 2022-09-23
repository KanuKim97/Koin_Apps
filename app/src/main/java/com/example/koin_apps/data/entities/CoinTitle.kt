package com.example.koin_apps.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoinTitle(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "coin_Number")
    val coin_no: Int,

    @ColumnInfo(name = "coinTitle")
    val coinTitle: String,

    @ColumnInfo(name = "isChecked")
    val isChecked: Boolean
)