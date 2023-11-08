package com.example.data.util

import com.example.data.database.tables.TickerTables
import com.example.domain.entity.db.TickerEntity

fun toTickerEntityMapper(tickerList: List<TickerTables>): List<TickerEntity> =
    tickerList.toList().map { ticker -> TickerEntity(ticker.Ticker) }

fun toTickerTableMapper(tickerEntity: TickerEntity): TickerTables =
    TickerTables(tickerEntity.ticker)