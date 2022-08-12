package com.example.koin_apps.data.remote

import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.remote.model.orderBook.OrderRoot
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import retrofit2.Call

object RetrofitRepo {

    fun getTickerSingleton(
        path: String
    ): Call<TickerRoot> {

        return RetrofitClient
            .getClient(Constants.IKoinPublicApiUri)
            .create(IKoinApiService::class.java)
            .getTicker(path)
    }

    fun getTransactionSingleton(
        path: String,
        count:Int
    ): Call<TransactionRoot> {

        return RetrofitClient
            .getClient(Constants.IKoinPublicApiUri)
            .create(IKoinApiService::class.java)
            .getTransactionHistory(path, count)
    }

    fun getOrderBookSingleton(
        path: String,
        count: Int
    ): Call<OrderRoot> {

        return RetrofitClient
            .getClient(Constants.IKoinPublicApiUri)
            .create(IKoinApiService::class.java)
            .getOrderBook(path, count)
    }

    /*
    fun getAssetsStatusSingleton(
        path: String
    ): Call<AssetsRoot> {

        return RetrofitClient
            .getClient(Constants.IKoinPublicApiUri)
            .create(IKoinApiService::class.java)
            .getAssetStatus(path)
    }
    */

}