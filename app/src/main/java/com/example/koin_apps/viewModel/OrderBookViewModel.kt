package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.remote.model.orderBook.OrderRoot

class OrderBookViewModel: ViewModel() {
    private val _orderBookLiveData: MutableLiveData<OrderRoot>?

    val orderBookLiveData: LiveData<OrderRoot>?
        get() = _orderBookLiveData

    init { _orderBookLiveData = null }

    override fun onCleared() {
        super.onCleared()
    }

    fun updateOrderBook(
        inputOrderData: OrderRoot
    ){
        _orderBookLiveData?.value = inputOrderData
    }

}