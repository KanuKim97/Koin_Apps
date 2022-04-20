package com.example.koin_apps.data.remote.model.ticker

class TickerData {
    var opening_price = 0
    var closing_price = 0
    var min_price = 0
    var max_price = 0
    var units_traded: Double ?= 0.0
    var acc_trade_value: Double ?= 0.0
    var prev_closing_price = 0
    var units_traded_24H: Double ?= 0.0
    var acc_trade_value_24H: Double ?= 0.0
    var fluctate_24H = 0
    var fluctate_rate_24H: Double ?=0.0
    var date: Double ?= 0.0
}