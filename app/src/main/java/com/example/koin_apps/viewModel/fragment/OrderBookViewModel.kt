package com.example.koin_apps.viewModel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.remote.model.orderBook.OrderData
import kotlinx.coroutines.launch

class OrderBookViewModel(private val repos: AppRepository): ViewModel() {
    private val _orderBookLiveData = MutableLiveData<OrderData>()
    val orderBookLiveData: LiveData<OrderData>
        get() = _orderBookLiveData

    fun getOrderBookData(path: String, count: Int) = viewModelScope.launch {
        val response = repos.getOrderBook(path, count)
        when(response.code()) {
            200 -> {
                try {
                    if(response.isSuccessful) { _orderBookLiveData.postValue(response.body()?.data) }
                } catch (e: NullPointerException) { throw (e) }
            }
        }
    }

}