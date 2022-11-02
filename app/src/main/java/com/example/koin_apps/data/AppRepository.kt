package com.example.koin_apps.data

import androidx.room.Room
import com.example.koin_apps.AndroidApp
import com.example.koin_apps.common.Constants.IKoinPublicApiUri
import com.example.koin_apps.data.entities.AppDataBase
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.model.orderBook.OrderRoot
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AppRepository {
    val koinApiService_public: IKoinApiService
        get() = getClient(IKoinPublicApiUri)
            .create(IKoinApiService::class.java)

    private var retrofit: Retrofit? = null

    //Create Retrofit Client
    private fun getClient(baseUrl: String): Retrofit {
        if(retrofit == null) {
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    //Bithumb-Ticker
    fun getTickerSingleton(
        path: String
    ): Call<TickerRoot> {
        return getClient(IKoinPublicApiUri)
            .create(IKoinApiService::class.java)
            .getTicker(path)
    }

    //Bithumb-Transaction
    fun getTransactionSingleton(
        path: String,
        count:Int
    ): Call<TransactionRoot> {
        return getClient(IKoinPublicApiUri)
            .create(IKoinApiService::class.java)
            .getTransactionHistory(path, count)
    }

    //Bithumb-OrderBook
    fun getOrderBookSingleton(
        path: String,
        count: Int
    ): Call<OrderRoot> {
        return getClient(IKoinPublicApiUri)
            .create(IKoinApiService::class.java)
            .getOrderBook(path, count)
    }

    //Create Room DB Client
    fun createAppDBClient(): AppDataBase {
        return Room.databaseBuilder(
            AndroidApp.getApplicationContext(),
            AppDataBase::class.java,
            "DB_v01"
        ).build()
    }

}