package com.example.koin_apps.common

import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.RetrofitClient

object Common {
    private const val KoinApiUrl_public = "https://api.bithumb.com/public/"

    val KoinApiService_public: IKoinApiService
        get() = RetrofitClient
            .getClient(KoinApiUrl_public)
            .create(IKoinApiService::class.java)

}