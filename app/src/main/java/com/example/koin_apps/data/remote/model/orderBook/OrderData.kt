package com.example.koin_apps.data.remote.model.orderBook

class OrderData {

    lateinit var timestamp: String
    lateinit var order_currency: String
    lateinit var payment_currency: String
    lateinit var bids: ArrayList<String>
    lateinit var asks: ArrayList<String>
    lateinit var quantity: String
    lateinit var price: String

}