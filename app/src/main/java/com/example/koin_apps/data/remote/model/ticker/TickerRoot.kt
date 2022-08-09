package com.example.koin_apps.data.remote.model.ticker

//Todo: Error Java.lang.illegalStateException -> might be TickerRoot.kt Error

data class TickerRoot(
    var status: String?,
    var data: Map<String?, Any?>,
    var message: String?
)