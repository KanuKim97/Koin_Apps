package com.example.koin_apps.common

import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.RetrofitClient
import com.example.koin_apps.data.remote.model.ticker.TickerData

object Common {
    private const val KoinApiUrl_public = "https://api.bithumb.com/public/"

    var curr_Result : TickerData? = null

    val KoinApiService_public : IKoinApiService
        get() = RetrofitClient
            .getClient(KoinApiUrl_public)
            .create(IKoinApiService::class.java)

}