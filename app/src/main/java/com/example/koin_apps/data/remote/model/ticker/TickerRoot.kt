package com.example.koin_apps.data.remote.model.ticker

data class TickerRoot(
    val status: String?,
    val data: Map<String?, Any?>,
    val message: String?
)

/*
class TickerRoot{
    var status: String? = null
    lateinit var data: Map<String?, TickerData?>
    //var data: TickerData?,
    var message: String? = null
}
*/
