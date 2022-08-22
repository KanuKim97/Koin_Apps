package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.remote.model.orderBook.OrderData
import com.example.koin_apps.data.remote.model.orderBook.OrderRoot
import java.lang.NullPointerException

class OrderBookViewModel: ViewModel() {
    private val _orderBookLiveData: MutableLiveData<OrderData>?

    val orderBookLiveData: LiveData<OrderData>?
        get() = _orderBookLiveData

    init { _orderBookLiveData = null }

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
    }
}