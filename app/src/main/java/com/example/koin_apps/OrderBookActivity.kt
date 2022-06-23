package com.example.koin_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.common.Common
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.RetrofitRepo
import com.example.koin_apps.data.remote.model.orderBook.OrderRoot
import com.example.koin_apps.databinding.ActivityOrderBookBinding
import com.example.koin_apps.viewModel.OrderBookViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderBookActivity : AppCompatActivity() {
    private lateinit var orderBookBinding: ActivityOrderBookBinding
    private lateinit var orderBookViewModel: OrderBookViewModel
    private lateinit var koinService: IKoinApiService

    var mOrderBookData: OrderRoot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orderBookBinding = ActivityOrderBookBinding.inflate(layoutInflater)
        orderBookViewModel = ViewModelProvider(this)[OrderBookViewModel::class.java]
        koinService = Common.KoinApiService_public

        setContentView(orderBookBinding.root)
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "Activity lifeCycle onResume is Called")

        val coinName: String = "BTC"
        val count: Int = 5

        orderBookResponse(coinName, count)

        orderBookViewModel.orderBookLiveData?.observe(
            this,
            { orderBookData ->

                if(orderBookData?.data == null) {

                    Toast.makeText(
                        applicationContext,
                        "OrderBook Data is Empty",
                        Toast.LENGTH_SHORT
                    ).show()

                } else { Log.d("order Book Data", "${orderBookData.data}") }

            })

    }


    private fun orderBookResponse(
        coinName: String,
        count: Int
    ){

        val mOrderBook = RetrofitRepo.getOrderBookSingleton(coinName, count)

        Log.d("function Called", "orderBook Response is Called")

        mOrderBook.enqueue(object: Callback<OrderRoot>{
            override fun onResponse(
                call: Call<OrderRoot>,
                response: Response<OrderRoot>
            ) {

                if(response.isSuccessful) {
                    Log.d("response", "response is Successful")
                } else {
                    Log.d("response", "response is Failed")
                }

            }

            override fun onFailure(call: Call<OrderRoot>, t: Throwable) { }
        })

    }

}