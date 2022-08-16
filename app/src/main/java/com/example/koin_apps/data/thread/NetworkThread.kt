package com.example.koin_apps.data.thread

interface NetworkThread {

    fun setTransactionThread(
        coinName: String,
        coinCount: Int
    )

    fun interruptThread()
}