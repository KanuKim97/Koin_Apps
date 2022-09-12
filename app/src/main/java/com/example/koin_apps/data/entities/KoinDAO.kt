package com.example.koin_apps.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "koin_list")
class KoinDAO {
    @ColumnInfo
    var coinName: String? = null

    @ColumnInfo
    var isChecked: Boolean = false

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var no: Long? = null
}