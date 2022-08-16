package com.example.koin_apps.data.remote.model.transaction

class TransactionRoot {
    lateinit var status: String
    var message: String? = null
    lateinit var data: ArrayList<TransactionData>
}