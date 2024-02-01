package com.koin.model.ticker


data class TickerData(
    val opening_price: String,
    val closing_price: String,
    val min_price: String,
    val max_price: String,
    val units_traded: String,
    val acc_trade_value: String,
    val prev_closing_price: String,
    val units_traded_24H: String,
    val acc_traded_value_24H: String,
    val fluctate_24H: String,
    val fluctate_rate_24H: String,
    val date: String
)