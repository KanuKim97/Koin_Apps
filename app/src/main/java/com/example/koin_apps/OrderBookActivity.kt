package com.example.koin_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.RetrofitClient
import com.example.koin_apps.data.remote.RetrofitRepo
import com.example.koin_apps.data.remote.model.orderBook.OrderRoot
import com.example.koin_apps.data.remote.model.requestError.RequestErrorRoot
import com.example.koin_apps.databinding.ActivityOrderBookBinding
import com.example.koin_apps.viewModel.OrderBookViewModel
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderBookActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var orderBookBinding: ActivityOrderBookBinding
    private lateinit var orderBookViewModel: OrderBookViewModel
    private lateinit var koinService: IKoinApiService

    var mOrderBookData: OrderRoot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orderBookBinding = ActivityOrderBookBinding.inflate(layoutInflater)
        orderBookViewModel = ViewModelProvider(this)[OrderBookViewModel::class.java]
        koinService = RetrofitClient.koinApiService_Public

        setContentView(orderBookBinding.root)
    }

    override fun onResume() {
        super.onResume()

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

        orderBookBinding.activeBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v?.id) {

            R.id.activeBtn ->
                orderBookResponse()

        }

    }

    private fun orderBookResponse() {

        val mOrderBook = RetrofitRepo.getOrderBookSingleton("aaa", 5)

        mOrderBook.enqueue(object: Callback<OrderRoot>{
            override fun onResponse(
                call: Call<OrderRoot>,
                response: Response<OrderRoot>
            ) {

                when(response.code()) {

                    200 -> {

                        mOrderBookData = response.body()

                        orderBookBinding.showOrderBook.text =
                            mOrderBookData?.data?.timestamp

                    }

                    400 -> {

                        val jsonObject: JSONObject

                        try {

                            jsonObject = JSONObject(response.errorBody()!!.string())

                            val responseCode = jsonObject.getString("status")
                            val responseMsg = jsonObject.getString("message")
                            val orderBookErr = RequestErrorRoot(responseCode, responseMsg)

                            //TODO OrderBook Error Handling
                            println(orderBookErr)

                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }

                    }

                }

            }

            override fun onFailure(call: Call<OrderRoot>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }

        })

    }


}