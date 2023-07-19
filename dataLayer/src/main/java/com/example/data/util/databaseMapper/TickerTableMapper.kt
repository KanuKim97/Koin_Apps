package com.example.data.util.databaseMapper

import com.example.data.database.tables.TickerTables
import com.example.domain.entity.TickerEntity

fun mapperToTickerTable(tickerEntity: TickerEntity): TickerTables =
    TickerTables(Ticker = tickerEntity.ticker)