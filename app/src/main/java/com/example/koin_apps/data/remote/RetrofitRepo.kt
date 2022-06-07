package com.example.koin_apps.data.remote

import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import retrofit2.Call

object RetrofitRepo {

    fun getTickerSingleton(path: String): Call<TickerRoot> {

        return RetrofitClient
            .getClient(Constants.IKoinApiUri)
            .create(IKoinApiService::class.java)
            .getTicker(path)

    }

    fun getTransactionSingleton(
        path: String,
        count:Int
    ): Call<TransactionRoot> {

        return RetrofitClient
            .getClient(Constants.IKoinApiUri)
            .create(IKoinApiService::class.java)
            .getTransactionHistory(path, count)

    }

}