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
import com.example.koin_apps.data.remote.model.errBody.ErrorBody
import com.example.koin_apps.data.remote.model.orderBook.OrderRoot
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
    var mOrderBookError: ErrorBody? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orderBookBinding = ActivityOrderBookBinding.inflate(layoutInflater)
        orderBookViewModel = ViewModelProvider(this)[OrderBookViewModel::class.java]
        koinService = RetrofitClient.koinApiService_Public

        setContentView(orderBookBinding.root)
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "Activity lifeCycle onResume is Called")

        orderBookBinding.activeBtn.setOnClickListener(this)

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

                if(response.isSuccessful) {
                    mOrderBookData = response.body()

                    Log.d("response", "response is Successful")
                    Log.d("responseCode", response.code().toString())
                    Log.d("responseStatus: ", mOrderBookData?.status.toString())
                    Log.d("responseData: ", mOrderBookData?.data.toString())

                    if(mOrderBookData!!.status != "0000") {

                        val jsonObject: JSONObject?

                        try {

                            jsonObject = JSONObject(response.errorBody().toString())

                            val errCode = jsonObject.getString("status")
                            val message = jsonObject.getString("message")

                            mOrderBookError = ErrorBody(errCode, message)

                            Log.d("Error Response", mOrderBookError.toString())

                        } catch (e: JSONException) {
                            Log.d("Error Exception", e.printStackTrace().toString())
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