package com.example.koin_apps.data.remote.model.orderBook

/*
class OrderData {
    lateinit var timestamp: String
    lateinit var order_currency: String
    lateinit var payment_currency: String

    lateinit var bids: ArrayList<OrderArrayObject>
    lateinit var asks: ArrayList<OrderArrayObject>

    lateinit var quantity: String
    lateinit var price: String
}
*/

data class OrderData(
    val status: String?,
    val ErrorMsg: String?,

    val timestamp: String?,
    val order_currency: String?,
    val payment_currency: String?,
    /*
    val bids: ArrayList<OrderArrayObject>
    val asks: ArrayList<OrderArrayObject>
    */
    val quantity: String?,
    val price: String?
)