package com.example.koin_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.remote.IKoinApiService
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orderBookBinding = ActivityOrderBookBinding.inflate(layoutInflater)

        orderBookViewModel = ViewModelProvider(this)[OrderBookViewModel::class.java]
        koinService = AppRepository.koinApiService_public


        setContentView(orderBookBinding.root)
    }

    override fun onResume() {
        super.onResume()

        orderBookViewModel.orderBookLiveData?.observe(this
        ) {
            if (it.status == "0000") {
                orderBookBinding.showOrderBook.text =
                    getString(
                        R.string.orderBook_Format,
                        it.price,
                        it.quantity,
                        it.timestamp,
                        it.order_currency,
                        it.payment_currency
                    )
            } else {
                orderBookBinding.showOrderBook.text = it.ErrorMsg
            }

        }

        orderBookBinding.activeBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v?.id) {
            R.id.activeBtn ->
                orderBookResponse()
        }

    }

    private fun orderBookResponse() {

        val mOrderBook = AppRepository.getOrderBookSingleton("BTC", 5)

        mOrderBook.enqueue(object: Callback<OrderRoot>{
            override fun onResponse(
                call: Call<OrderRoot>,
                response: Response<OrderRoot>
            ) {
                when(response.code()) {

                    200 -> orderBookViewModel.updateOrderBook(response.body())

                    400 -> {
                        val jsonObject: JSONObject

                        try {
                            jsonObject = JSONObject(response.errorBody()!!.string())

                            val responseCode = jsonObject.getString("status")
                            val responseMsg = jsonObject.getString("message")

                            orderBookViewModel.updateErrorOrder(responseCode, responseMsg)
                        } catch (e: JSONException) { e.printStackTrace() }
                    }
                }
            }

            override fun onFailure(
                call: Call<OrderRoot>,
                t: Throwable
            ) { orderBookViewModel.updateErrorOrder(t.cause.toString(), t.message.toString()) }

        })

    }


}