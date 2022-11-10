package com.example.koin_apps.viewModel.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.remote.model.orderBook.OrderData
import com.example.koin_apps.data.remote.model.orderBook.OrderRoot
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NullPointerException

class OrderBookViewModel(private val repos: AppRepository): ViewModel() {
    private val _orderBookLiveData: MutableLiveData<OrderData>?

    val orderBookLiveData: LiveData<OrderData>?
        get() = _orderBookLiveData

    init { _orderBookLiveData = null }

    fun getOrderData(path: String, count: Int) {
        repos.getOrderBook(path, count).enqueue(object: Callback<OrderRoot> {
            override fun onResponse(
                call: Call<OrderRoot>,
                response: Response<OrderRoot>
            ) {
                when(response.code()) {
                    200 -> {
                        try {
                            val coinOrderDesc = response.body()

                            Log.d("OrderBook", "$coinOrderDesc")
                        } catch (e: NullPointerException) {
                            throw NullPointerException("Response Data is Null or Empty")
                        }
                    }

                    400 -> {
                        val errJsonObj: JSONObject

                        try{
                            errJsonObj = JSONObject(response.errorBody()?.string()!!)
                            val responseErrCode = errJsonObj.getString("status")
                            val responseErrMsg = errJsonObj.getString("message")

                            Log.d("400 Error", "$responseErrCode: $responseErrMsg")
                        } catch (e: JSONException) { e.printStackTrace() }
                    }
                }
            }

            override fun onFailure(call: Call<OrderRoot>, t: Throwable) { t.printStackTrace() }
        })
    }
/*
    fun updateOrderBook(
        inputOrderData: OrderRoot?
    ){
        if(inputOrderData != null) {
            _orderBookLiveData?.value =
                OrderData(
                    inputOrderData.status,
                    inputOrderData.message,
                    inputOrderData.data?.timestamp,
                    inputOrderData.data?.order_currency,
                    inputOrderData.data?.payment_currency,
                    inputOrderData.data?.quantity,
                    inputOrderData.data?.price
                )
        } else { throw NullPointerException("Response Data is Empty") }
    }

    fun updateErrorOrder(
        inputErrorCode: String,
        inputErrorMsg: String
    ){
        _orderBookLiveData?.value =
            OrderData(
                inputErrorCode,
                inputErrorMsg,
                null,
                null,
                null,
                null,
                null
            )
    }*/
}