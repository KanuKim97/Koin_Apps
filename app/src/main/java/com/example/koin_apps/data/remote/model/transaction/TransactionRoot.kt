package com.example.koin_apps.data.remote.model.transaction

class TransactionRoot {
    lateinit var status: String
    lateinit var message: String
    lateinit var data: ArrayList<TransactionData>
}