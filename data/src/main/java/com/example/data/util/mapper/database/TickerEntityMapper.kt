package com.example.data.util.mapper.database

import com.example.data.database.tables.TickerTables
import com.example.domain.entity.TickerEntity

fun mapperToTickerEntity(
    tickers: List<TickerTables>
): List<TickerEntity> = tickers.toList().map { TickerEntity(ticker = it.Ticker) }